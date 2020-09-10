package nl.hu.bep2.casino.blackjack.domain;

import java.util.Objects;

public class Card {
	private Faces face;
	private Rank rank;

	public Card(Faces face, Rank rank) {
		this.face = face;
		this.rank = rank;
	}

	public String toString() {
		return face+" "+ rank;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return face == card.face &&
				rank.equals(card.rank);
	}

	@Override
	public int hashCode() {
		return Objects.hash(face, rank);
	}
}
