package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.application.strategies.HandStrategie;
import nl.hu.bep2.casino.blackjack.data.SpringHandRepository;
import nl.hu.bep2.casino.blackjack.data.SpringTableRepository;
import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.Player;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class HandService {
	private final SpringHandRepository handRepository;
	private final SpringTableRepository tableRepository;

	public HandService(SpringHandRepository handRepository,SpringTableRepository tableRepository) {
		this.tableRepository=tableRepository;
		this.handRepository = handRepository;
		System.out.println("HANDREPOSITORY:"+handRepository);
	}
	public Hand newHand() {
		Hand hand=new Hand();
		this.handRepository.save(hand);
		return hand;
	}
	public void executeAction(Player player,HandStrategie strategy) {
		Hand hand=player.getHand();
		strategy.doStrategy(hand,player,tableRepository.findByPlayer(player).get());
		handRepository.save(hand);
	}
}
