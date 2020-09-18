package nl.hu.bep2.casino.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private Shoe shoe;
	private List<Player> players =new ArrayList<>();
	private boolean started=false;
	public Table(Shoe shoe) {
		this.shoe=shoe;
	}
	public void addPlayer(Player player) {
		players.add(player);
	}
	public void start() {
		if(this.started) throw new UnsupportedOperationException("al gestart!");
		this.started=true;
		players.add(new Dealer(this));
	}
	public Shoe getShoe() {
		return this.shoe;
	}
}
