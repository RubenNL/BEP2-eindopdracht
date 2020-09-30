package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;

public interface HandStrategie {
	public void doStrategy(Hand hand);
}
