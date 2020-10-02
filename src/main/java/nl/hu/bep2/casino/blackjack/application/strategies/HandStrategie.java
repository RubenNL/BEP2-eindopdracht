package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.blackjack.domain.Player;

public interface HandStrategie {
	public void doStrategy(Hand hand, Player player, PlayTable playTable);
}
