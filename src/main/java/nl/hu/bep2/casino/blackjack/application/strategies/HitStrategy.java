package nl.hu.bep2.casino.blackjack.application.strategies;

import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.Shoe;

public class HitStrategy implements HandStrategie {
	private Shoe shoe;
	public HitStrategy(Shoe shoe) {
		this.shoe=shoe;
	}
	@Override
	public void doStrategy(Hand hand) {
		hand.addCard(shoe.grabCard());
	}
}
