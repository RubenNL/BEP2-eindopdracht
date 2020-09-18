package nl.hu.bep2.casino.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private Shoe shoe;
	private Player player;
	private Dealer dealer;
	private boolean started=false;
	public Table(Shoe shoe) {
		this.shoe=shoe;
		dealer=new Dealer(this);
	}
	public void setPlayer(Player player) {
		if(this.player!=null) throw new UnsupportedOperationException("player al defined!");
		this.player=player;
	}
	public Shoe getShoe() {
		return this.shoe;
	}
}
