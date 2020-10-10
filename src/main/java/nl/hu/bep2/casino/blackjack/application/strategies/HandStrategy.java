package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;

public interface HandStrategy {
	//pattern: strategy(behavioral)
	void doStrategy(Hand hand, PlayTable playTable);
}
