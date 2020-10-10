package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.application.ShoeService;
import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import org.springframework.stereotype.Component;

@Component
public class HitStrategy implements HandStrategy {
	public HitStrategy(ShoeService shoeService) {
		this.shoeService=shoeService;
	}
	private final ShoeService shoeService;
	@Override
	public void doStrategy(Hand hand, PlayTable table) {
		hand.addCard(shoeService.grabCard(table));
	}
}
