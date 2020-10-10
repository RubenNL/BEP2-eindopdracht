package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Hand;

import java.util.List;

public class HandDTO {
	//pattern: DTO (structual)
	public HandStates state;
	public final List<Card> cards;
	public final List<Integer> possibleValues;
	public HandDTO(Hand hand,boolean showAllCards) {
		if(hand.getCards().size()==0) state=HandStates.EMPTY;
		else if(hand.hasBlackjack()) state=HandStates.BLACKJACK;
		else if(hand.isBust()) state=HandStates.BUST;
		else if(hand.isFinished()) state=HandStates.STAND;
		else state=HandStates.PLAYING;
		if(state==HandStates.EMPTY || showAllCards || hand.hasBlackjack()) cards=hand.getCards();
		else cards=hand.getCards().subList(0,1);
		if(cards.size()==1) state=HandStates.HIDDEN;
		possibleValues=Hand.getPossibleTotalValues(cards);
	}
}