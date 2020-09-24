package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.Entity;

@Entity
public class Dealer extends Player {
	public Dealer(Table table) {super(table);}

	public Dealer() {

	}
}
