package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.application.strategies.HandStrategie;
import nl.hu.bep2.casino.blackjack.application.strategies.HitStrategy;
import nl.hu.bep2.casino.blackjack.data.SpringHandRepository;
import nl.hu.bep2.casino.blackjack.data.SpringTableRepository;
import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.blackjack.domain.Shoe;
import nl.hu.bep2.casino.blackjack.exceptions.FundsException;
import nl.hu.bep2.casino.blackjack.exceptions.GameStateException;
import nl.hu.bep2.casino.chips.application.ChipsService;
import nl.hu.bep2.casino.security.data.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class BlackjackService {
	private final SpringTableRepository tableRepository;
	private final SpringHandRepository handRepository;
	private final HitStrategy hitStrategy;
	private final ChipsService chipsService;
	public BlackjackService(SpringTableRepository tableRepository, SpringHandRepository handRepository, HitStrategy hitStrategy, ChipsService chipsService) {
		this.tableRepository = tableRepository;
		this.handRepository=handRepository;
		this.hitStrategy=hitStrategy;
		this.chipsService=chipsService;
	}
	private boolean hasTable(User user) {
		return tableRepository.findByUser(user).isPresent();
	}
	public PlayTable newTable(int decks, User user) {
		PlayTable table = new PlayTable(new Shoe(decks));
		table.setUser(user);
		table=tableRepository.saveAndFlush(table);
		return table;
	}
	public void startRound(Long id, Long bet) {
		PlayTable table=getTable(id);
		if(!chipsService.withdraw(table.getUser().getUsername(),bet)) throw new FundsException("NOT ENOUGH CHIPS!");
		table.setBet(bet);
		executeAction(table.getPlayerHand(), hitStrategy);
		executeAction(table.getPlayerHand(), hitStrategy);
		executeAction(table.getDealerHand(),hitStrategy);
		executeAction(table.getDealerHand(),hitStrategy);
		tableRepository.save(table);
	}
	public PlayTable getTable(Long id) {
		return tableRepository.findById(id).get();
	}
	public void deleteTable(Long id) {
		tableRepository.deleteById(id);
	}
	public void executeAction(Hand hand, HandStrategie strategy) {
		if(hand.isFinished()) throw new GameStateException("already standing!");
		if(hand.isBust()) throw new GameStateException("bust!");
		if(hand.getTable().getBet()==0) throw new GameStateException("niet gestart!");
		strategy.doStrategy(hand,hand.getTable());
		handRepository.save(hand);
		if(hand.getTable().getPlayerHand().equals(hand)) {
			if (hand.hasBlackjack()) {
				chipsService.depositChips(hand.getTable().getUser().getUsername(), (long) (hand.getTable().getBet() * 2.5));
				hand.getTable().setBet(0L);
			}
			if(hand.isBust()) hand.getTable().setBet(0L);
		}
	}
	public void executeAction(Long id,HandStrategie strategy) {
		executeAction(getTable(id).getPlayerHand(),strategy);
	}
	public void dealerStep(Long id) {
		PlayTable table=getTable(id);
		Hand dealerHand=table.getDealerHand();
		Hand playerHand=table.getPlayerHand();
		if(table.getBet()==0) {
			dealerHand.reset();
			playerHand.reset();
			return;
		}
		boolean dealerDone=false;
		if(dealerHand.getPossibleTotalValues().contains(21)) dealerDone=true;
		for(int value:dealerHand.getPossibleTotalValues()) {
			if(value>16 && value<22) dealerDone=true;
		}
		if(dealerDone) {
			if(playerHand.distanceTo21() < dealerHand.distanceTo21()) chipsService.depositChips(table.getUser().getUsername(),table.getBet()*2);//PLAYER CLOSER TO 21
			if(playerHand.distanceTo21() == dealerHand.distanceTo21()) chipsService.depositChips(table.getUser().getUsername(), table.getBet());//PUSH
			table.setBet(0L);
		} else executeAction(dealerHand,hitStrategy);
		handRepository.save(dealerHand);
	}
}
