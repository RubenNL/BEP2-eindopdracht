package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.application.ShoeService;
import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.blackjack.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HitStrategy implements HandStrategie {
	@Autowired
	private ShoeService shoeService;
	@Override
	public void doStrategy(Hand hand, Player player, PlayTable table) {
		System.out.println(hand);
		System.out.println(shoeService);
		hand.addCard(shoeService.grabCard(table));
	}
}
