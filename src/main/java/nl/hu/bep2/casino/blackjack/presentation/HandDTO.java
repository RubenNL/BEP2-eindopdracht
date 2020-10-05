package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Hand;

import java.util.List;

public class HandDTO {
	public final HandStates state;
	public final List<Card> cards;
	public final List<Integer> possibleValues;
	public HandDTO(Hand hand,boolean showAllCards) {
		possibleValues=hand.getPossibleTotalValues();
		if(possibleValues.size()==0) state=HandStates.EMPTY;
		else if(hand.hasBlackjack()) state=HandStates.BLACKJACK;
		else if(hand.isBust()) state=HandStates.BUST;
		else state=HandStates.PLAYING;
		if(state==HandStates.EMPTY || showAllCards || hand.hasBlackjack()) cards=hand.getCards();
		else cards=hand.getCards().subList(0,1);
	}
}