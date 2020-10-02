package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.blackjack.domain.Player;

public class StandStrategy implements HandStrategie {
	@Override
	public void doStrategy(Hand hand, Player player, PlayTable table) {
		hand.finish();
	}
}
