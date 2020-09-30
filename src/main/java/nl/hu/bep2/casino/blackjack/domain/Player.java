package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Player {
	@Id
	private Integer id;
	@OneToOne
	private PlayTable playTable;
	@OneToOne
	private Hand hand;
	public Player(PlayTable playTable) {
		this.playTable = playTable;
	}

	public Player() {

	}

	public PlayTable getTable() {
		return this.playTable;
	}
	public Hand getHand() {
		return this.hand;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
