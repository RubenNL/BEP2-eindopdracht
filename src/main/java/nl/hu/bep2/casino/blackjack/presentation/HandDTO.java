package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Hand;

import java.util.List;

public class HandDTO {
	public final HandStates state;
	public final List<Card> cards;
	public HandDTO(Hand hand,boolean showAllCards) {
		if(hand.hasBlackjack()) state=HandStates.BLACKJACK;
		else if(hand.isBust()) state=HandStates.BUST;
		else state=HandStates.PLAYING;
		if(showAllCards || hand.hasBlackjack()) cards=hand.getCards();
		else cards=hand.getCards().subList(0,1);
	}
}
