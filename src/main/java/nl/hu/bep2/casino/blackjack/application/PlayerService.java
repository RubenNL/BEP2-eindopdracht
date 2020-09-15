package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.domain.Card;
import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.HandBet;
import nl.hu.bep2.casino.blackjack.domain.Player;

import java.util.List;

public class PlayerService {
	private Player player;
	public PlayerService(Player player) {
		this.player=player;
	}
	public void split(HandBet hand) {
		List<Card> cards=hand.getCards();
		if(cards.size()==2 && cards.get(0).getRank().getPossibleValues().equals(cards.get(1).getRank().getPossibleValues())) {
			player.removeHand(hand);
			Hand hand1=new HandBet(player,hand.getBet());
			hand1.addCard(cards.get(0));
			hit(hand1);
			player.addHand(hand1);
			Hand hand2=new HandBet(player,hand.getBet());
			hand2.addCard(cards.get(1));
			hit(hand2);
			player.addHand(hand2);
		} else throw new IllegalArgumentException("Hand kan niet gesplitst worden.");
	}
	public void doubleDown(HandBet hand) {
		if(hand.getCards().size()>2) throw new IllegalArgumentException("Double down kan alleen als eerste beurt!");
		hand.doubleBet();
		hit(hand);
		hand.stand();
	}
	public void hit(Hand hand) {
		hand.addCard(player.getTable().getShoe().grabCard());
	}
}
