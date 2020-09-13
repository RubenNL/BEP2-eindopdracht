package nl.hu.bep2.casino.blackjack.domain;

import java.util.Collections;
import java.util.List;

public class Player extends DealerAndPlayer {
	private List<Hand> hands;
	public Player(Table table) {
		super(table);
	}
	public void removeHand(Hand hand) {
		hands.remove(hand);
	}
	public void addHand(Hand hand) {this.hands.add(hand);}
	public List<Hand> getHands() {
		return Collections.unmodifiableList(hands);
	}
}
