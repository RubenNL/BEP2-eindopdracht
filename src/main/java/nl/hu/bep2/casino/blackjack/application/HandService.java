package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.data.SpringHandRepository;
import nl.hu.bep2.casino.blackjack.domain.Hand;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class HandService {
	private final SpringHandRepository handRepository;

	public HandService(SpringHandRepository handRepository) {
		this.handRepository = handRepository;
	}
	public Hand newHand() {
		Hand hand=new Hand();
		this.handRepository.save(hand);
		return hand;
	}
}
