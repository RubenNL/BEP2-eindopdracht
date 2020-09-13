package nl.hu.bep2.casino.blackjack.domain;

import java.util.*;

public class Shoe {
	private int decks;
	private long seed;
	private int cardsTaken;
	private List<Card> cards;
	public Shoe(int decks) {
		this(decks,new Random().nextLong(),0);
	}
	public Shoe(int decks,long seed,int cardsTaken) {
		this.decks=decks;
		this.seed=seed;
		this.cardsTaken=cardsTaken;
		generate();
	}
	public int cardsAvailable() {
		return cards.size();
	}
	public Card grabCard() {
		return cards.remove(0);
	}
	private void generate() {
		cards=new ArrayList<>();
		for(int i=0;i<decks;i++) {
			for (Faces face : Faces.values()) {
				for (Rank rank : Rank.getRanks()) {
					cards.add(new Card(face, rank));
				}
			}
		}
		Collections.shuffle(cards,new Random(seed));
		for(int i=0;i<cardsTaken;i++) grabCard();
	}
	public long getSeed() {return this.seed;}
	public int getCardsTaken() {return this.decks*52-this.cards.size();}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Shoe shoe = (Shoe) o;
		return decks == shoe.decks &&
				seed == shoe.seed &&
				cardsTaken == shoe.cardsTaken;
	}

	@Override
	public int hashCode() {
		return Objects.hash(decks, seed, cardsTaken);
	}

	@Override
	public String toString() {
		return "Shoe{" +
				"decks=" + decks +
				", seed=" + seed +
				", cardsTaken=" + cardsTaken +
				'}';
	}
}
