package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.chips.application.ChipsService;
import org.springframework.stereotype.Component;

@Component
public class SurrenderStrategy implements HandStrategie {
	private final StandStrategy standStrategy;
	private final ChipsService chipsService;
	public SurrenderStrategy(StandStrategy standStrategy,ChipsService chipsService) {
		this.standStrategy=standStrategy;
		this.chipsService=chipsService;
	}
	public void doStrategy(Hand hand, PlayTable playTable) {
		standStrategy.doStrategy(hand,playTable);
		chipsService.depositChips(playTable.getUser().getUsername(), (long) (playTable.getBet()*0.5));
		playTable.setBet(0L);
	}
}
