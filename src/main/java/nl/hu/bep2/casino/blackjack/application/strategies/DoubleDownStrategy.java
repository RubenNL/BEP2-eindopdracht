package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.blackjack.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;

public class DoubleDownStrategy implements HandStrategie {
	@Autowired
	private HandStrategie hitStrategy;
	@Autowired
	private StandStrategy standStrategy;
	@Override
	public void doStrategy(Hand hand, Player player, PlayTable table) {
		hitStrategy.doStrategy(hand,player,table);
		hand.doubleBet();
		standStrategy.doStrategy(hand,player,table);
	}
}
