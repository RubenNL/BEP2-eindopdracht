package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;

public class StandStrategy implements HandStrategie {
	@Override
	public void doStrategy(Hand hand) {
		hand.finish();
	}
}
