package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Hand {
	private @Id @GeneratedValue int id;
	@Convert(converter= CardsConverter.class)
	private List<Card> cards=new ArrayList<>();
	@OneToOne(mappedBy="hand", cascade=CascadeType.ALL)
	private Player player;
	private boolean finished;
	private int bet;
	private List<Integer> getPossibleTotalValues(List<Card> cards) {
		List<Integer> values=new ArrayList<>();
		List<Integer> currentCardValues=cards.get(0).getRank().possibleValues;
		if(cards.size()>1) {
			List<Integer> valuesOtherCards= getPossibleTotalValues(cards.subList(1,cards.size()));
			for(Integer value:currentCardValues) {
				for(Integer otherValue:valuesOtherCards) {
					values.add(value+otherValue);
				}
			}
		} else values=currentCardValues;
		Collections.sort(values);
		return values;
	}
	public List<Integer> getPossibleTotalValues() {
		return getPossibleTotalValues(cards);
	}
	public int getMinValue() {
		return getPossibleTotalValues().get(0);
	}
	public int getMaxValue() {
		List<Integer> possibleValues= getPossibleTotalValues();
		return possibleValues.get(possibleValues.size()-1);
	}
	public int closestTo21() {
		List<Integer> possibleValues= getPossibleTotalValues();
		Collections.reverse(possibleValues);
		while(true) {
			int value=possibleValues.remove(0);
			if(value<22) return value;
		}
	}
	public int distanceTo21() {
		return 21-closestTo21();
	}
	public boolean isBust() {
		return getMinValue()>21;
	}
	public boolean hasBlackjack() {
		return cards.size()==2 && getMaxValue()==21;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}
	public void finish() {this.finished=true;}
	public boolean getFinished() {return this.finished;}
	public int getBet() {
		return this.bet;
	}
	public void doubleBet() {
		this.bet*=2;
	}
	public int getId() {return this.id;}
	public Player getPlayer() {return this.player;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Hand hand = (Hand) o;
		return id == hand.id &&
				finished == hand.finished &&
				bet == hand.bet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, finished, bet);
	}

	@Override
	public String toString() {
		return "Hand{" +
				"id=" + id +
				", finished=" + finished +
				", bet=" + bet +
				'}';
	}
	public void reset() {
		this.cards.clear();
	}
}
