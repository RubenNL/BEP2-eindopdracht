package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.application.strategies.HandStrategie;
import nl.hu.bep2.casino.blackjack.application.strategies.HitStrategy;
import nl.hu.bep2.casino.blackjack.data.SpringHandRepository;
import nl.hu.bep2.casino.blackjack.data.SpringShoeRepository;
import nl.hu.bep2.casino.blackjack.data.SpringTableRepository;
import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.blackjack.domain.Shoe;
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
	public BlackjackService(SpringTableRepository tableRepository, SpringShoeRepository shoeRepository, SpringHandRepository handRepository, HitStrategy hitStrategy) {
		this.tableRepository = tableRepository;
		this.shoeRepository=shoeRepository;
		this.handRepository=handRepository;
		this.hitStrategy=hitStrategy;
	}
	private PlayTable firstJoin(int decks,User user) {
		PlayTable table = new PlayTable(new Shoe(decks));
		table.setUser(user);
		tableRepository.save(table);
		return table;
	}
	private boolean hasTable(User user) {
		return tableRepository.findByUser(user).isPresent();
	}
	public PlayTable newTable(int decks, User user) {
		if(!hasTable(user)) firstJoin(decks,user);
		System.out.println("RESETTING TABLE!");
		PlayTable table=getTable(user);
		shoeRepository.findByTable(table).get().reset();
		table.reset();
		tableRepository.save(table);
		table=getTable(user);
		executeAction(table.getPlayerHand(), hitStrategy);
		executeAction(table.getPlayerHand(), hitStrategy);
		executeAction(table.getDealerHand(),hitStrategy);
		tableRepository.save(table);
		return table;
	}
	public PlayTable getTable(User user) {
		return tableRepository.findByUser(user).get();
	}
	public void executeAction(Hand hand, HandStrategie strategy) {
		System.out.println("HAND-TABLE:"+hand.getTable());
		strategy.doStrategy(hand,hand.getTable());
		handRepository.save(hand);
	}
}
