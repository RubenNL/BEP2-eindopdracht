package nl.hu.bep2.casino.blackjack.domain;

import java.util.Objects;

public class Card {
	private Faces face;
	private Types type;

	public Card(Faces face, Types type) {
		this.face = face;
		this.type = type;
	}

	public String toString() {
		return face+" "+type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return face == card.face &&
				type == card.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(face, type);
	}
}
