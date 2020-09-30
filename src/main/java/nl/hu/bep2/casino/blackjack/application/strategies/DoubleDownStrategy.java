package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;

public class DoubleDownStrategy implements HandStrategie {
	private HandStrategie hitStrategy;
	public DoubleDownStrategy(HandStrategie hitStrategy) {
		this.hitStrategy=hitStrategy;
	}
	@Override
	public void doStrategy(Hand hand) {
		hitStrategy.doStrategy(hand);
		hand.doubleBet();
	}
}
