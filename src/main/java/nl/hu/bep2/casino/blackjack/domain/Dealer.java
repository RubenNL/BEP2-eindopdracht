package nl.hu.bep2.casino.blackjack.domain;

public class Dealer extends DealerAndPlayer {
	private Hand hand;
	public Dealer(Table table) {super(table);}

	public Hand getHand() {
		return hand;
	}
}
