package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="shoes")
public class Shoe {
	private int decks;
	private long seed;
	private int cardsTaken;
	@Transient
	private List<Card> cards;
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne(mappedBy="shoe")
	private PlayTable table;
	public Shoe(int decks) {
		this(decks,new Random().nextLong(),0);
	}
	public Shoe(int decks,long seed,int cardsTaken) {
		this.decks=decks;
		this.seed=seed;
		this.cardsTaken=cardsTaken;
		generate();
	}

	public Shoe() {

	}
	public int cardsAvailable() {
		return cards.size();
	}
	public Card grabCard() {
		cardsTaken++;
		return cards.remove(0);
	}
	@PostLoad
	private void generate() {
		cards=new ArrayList<>();
		for(int i=0;i<decks;i++) {
			for (Faces face : Faces.values()) {
				for (Rank rank : Rank.values()) {
					cards.add(new Card(face, rank));
				}
			}
		}
		Collections.shuffle(cards,new Random(seed));
		for(int i=0;i<cardsTaken;i++) cards.remove(0);
	}
	public void setSeed(long seed) {this.seed=seed;}
	public long getSeed() {return this.seed;}
	public void setCardsTaken(int cardsTaken) {this.cardsTaken=cardsTaken;}
	public int getCardsTaken() {return cardsTaken;}

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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void reset() {
		this.seed=new Random().nextLong();
		this.cards.clear();
		this.cardsTaken=0;
		generate();
	}
}
