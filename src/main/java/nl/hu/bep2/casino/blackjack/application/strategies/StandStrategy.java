package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import org.springframework.stereotype.Component;

@Component
public class StandStrategy implements HandStrategy {
	@Override
	public void doStrategy(Hand hand, PlayTable table) {
		hand.finish();
	}
}
