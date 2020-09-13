package nl.hu.bep2.casino.blackjack.domain;

public class DealerAndPlayer {//TODO moet nog een goede naam voor worden bedacht.
	private Table table;
	public DealerAndPlayer(Table table) {
		this.table=table;
	}
	public Table getTable() {
		return this.table;
	}
}
