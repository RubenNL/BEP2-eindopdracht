package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;

public class StandStrategy implements HandStrategie {
	@Override
	public void doStrategy(Hand hand, PlayTable table) {
		hand.finish();
	}
}
