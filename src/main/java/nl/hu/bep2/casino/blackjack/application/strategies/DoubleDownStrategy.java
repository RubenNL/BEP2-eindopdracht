package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.blackjack.exceptions.FundsException;
import nl.hu.bep2.casino.chips.application.ChipsService;
import org.springframework.stereotype.Component;

@Component
public class DoubleDownStrategy implements HandStrategie {
	private final HitStrategy hitStrategy;
	private final StandStrategy standStrategy;
	private final ChipsService chipsService;
	public DoubleDownStrategy(HitStrategy hitStrategy,StandStrategy standStrategy,ChipsService chipsService) {
		this.hitStrategy=hitStrategy;
		this.standStrategy=standStrategy;
		this.chipsService=chipsService;
	}
	@Override
	public void doStrategy(Hand hand, PlayTable table) {
		if(!chipsService.withdraw(table.getUser().getUsername(), table.getBet())) throw new FundsException("Not enough chips");
		hitStrategy.doStrategy(hand,table);
		table.doubleBet();
		standStrategy.doStrategy(hand,table);
	}
}
