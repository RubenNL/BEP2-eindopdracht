package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.application.strategies.HandStrategie;
import nl.hu.bep2.casino.blackjack.application.strategies.HitStrategy;
import nl.hu.bep2.casino.blackjack.data.SpringHandRepository;
import nl.hu.bep2.casino.blackjack.data.SpringShoeRepository;
import nl.hu.bep2.casino.blackjack.data.SpringTableRepository;
import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.blackjack.domain.Shoe;
import nl.hu.bep2.casino.chips.application.ChipsService;
import nl.hu.bep2.casino.security.data.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class BlackjackService {
	private final SpringTableRepository tableRepository;
	private final SpringShoeRepository shoeRepository;
	private final SpringHandRepository handRepository;
	private final HitStrategy hitStrategy;
	private final ChipsService chipsService;
	public BlackjackService(SpringTableRepository tableRepository, SpringShoeRepository shoeRepository, SpringHandRepository handRepository, HitStrategy hitStrategy, ChipsService chipsService) {
		this.tableRepository = tableRepository;
		this.shoeRepository=shoeRepository;
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
		if(!chipsService.withdraw(table.getUser().getUsername(),bet)) throw new IllegalArgumentException("NOT ENOUGH CHIPS!");
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
		if(hand.getTable().isPlayerFinished()) throw new UnsupportedOperationException("al gestopt!");
		strategy.doStrategy(hand,hand.getTable());
		handRepository.save(hand);
	}
	public void executeAction(Long id,HandStrategie strategy) {
		executeAction(getTable(id).getPlayerHand(),strategy);
	}
}
