package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Player {
	@Id
	private Integer id;
	@OneToOne
	private Table table;
	@OneToOne
	private Hand hand;
	public Player(Table table) {
		this.table=table;
	}

	public Player() {

	}

	public Table getTable() {
		return this.table;
	}
	public Hand getHand() {
		return this.hand;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	public Integer getId() {
		return id;
	}
}
