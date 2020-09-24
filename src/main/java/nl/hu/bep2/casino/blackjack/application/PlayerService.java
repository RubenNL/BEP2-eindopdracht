package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.domain.*;

import java.util.List;

public class PlayerService {
	public void doubleDown(HumanPlayer player) {
		if(player.getHand().getCards().size()>2) throw new IllegalArgumentException("Double down kan alleen als eerste beurt!");
		player.doubleBet();
		hit(player);
		player.stand();
	}

	public void hit(Player player) {
		player.getHand().addCard(player.getTable().getShoe().grabCard());
	}
}
