package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.security.data.User;

import javax.persistence.OneToOne;

public class HumanPlayer extends Player {
	public HumanPlayer(User user,PlayTable playTable) {
		super(playTable);
		super.setUser(user);
	}
}
