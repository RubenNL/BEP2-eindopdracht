package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import org.springframework.stereotype.Component;

@Component
public class DoubleDownStrategy implements HandStrategie {
	private HitStrategy hitStrategy;
	private StandStrategy standStrategy;
	public DoubleDownStrategy(HitStrategy hitStrategy,StandStrategy standStrategy) {
		this.hitStrategy=hitStrategy;
		this.standStrategy=standStrategy;
	}
	@Override
	public void doStrategy(Hand hand, PlayTable table) {
		hitStrategy.doStrategy(hand,table);
		table.doubleBet();
		standStrategy.doStrategy(hand,table);
	}
}
