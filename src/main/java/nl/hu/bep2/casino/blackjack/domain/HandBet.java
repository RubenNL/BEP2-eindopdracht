package nl.hu.bep2.casino.blackjack.domain;

public class HandBet extends Hand {
	private int bet;
	private Hand hand;

	public HandBet(DealerAndPlayer dealerAndPlayer,int bet) {
		super(dealerAndPlayer);
		this.bet=bet;
	}
	public int getBet() {
		return this.bet;
	}
	public void doubleBet() {
		this.bet*=2;
	}

}
