package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import org.springframework.beans.factory.annotation.Autowired;

public class DoubleDownStrategy implements HandStrategie {
	@Autowired
	private HandStrategie hitStrategy;
	@Autowired
	private StandStrategy standStrategy;
	@Override
	public void doStrategy(Hand hand, PlayTable table) {
		hitStrategy.doStrategy(hand,table);
		table.doubleBet();
		standStrategy.doStrategy(hand,table);
	}
}
