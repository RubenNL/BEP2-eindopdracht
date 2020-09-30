package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.Entity;

@Entity
public class HumanPlayer extends Player {
	public HumanPlayer(PlayTable playTable) {
		super(playTable);
	}
	public HumanPlayer() {

	}
}
