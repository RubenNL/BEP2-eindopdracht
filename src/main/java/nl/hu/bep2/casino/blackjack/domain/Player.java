package nl.hu.bep2.casino.blackjack.domain;

public class Player {
	private Table table;
	public Player(Table table) {
		this.table=table;
	}
	public Table getTable() {
		return this.table;
	}
	private Hand hand;
	public Hand getHand() {
		return this.hand;
	}
}
