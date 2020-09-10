package nl.hu.bep2.casino.blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Shoe {
	private int decks;
	private long seed;
	private int cardsTaken;
	private ArrayList<Card> cards;
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
		return decks*52-cards.size();
	}
	public Card grabCard() {
		return cards.remove(0);
	}
	private void generate() {
		cards=new ArrayList<>();
		for(Faces face:Faces.values()) {
			for(Rank rank:Rank.getRanks()) {
				cards.add(new Card(face,rank));
			}
		}
		Collections.shuffle(cards,new Random(seed));
		for(int i=0;i<cardsTaken;i++) grabCard();
	}
	public long getSeed() {return this.seed;}
	public int getCardsTaken() {return this.cardsTaken;}
}
