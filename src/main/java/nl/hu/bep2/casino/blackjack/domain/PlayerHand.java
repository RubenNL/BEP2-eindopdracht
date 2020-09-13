package nl.hu.bep2.casino.blackjack.domain;

import java.util.List;

public class PlayerHand extends Hand {
	private int bet;
	private Player player;
	public PlayerHand(Player player, int bet) {
		super(player);
		this.player=player;
		this.bet=bet;
	}
	public void split(Hand hand) {
		List<Card> cards=hand.getCards();
		if(cards.size()==2 && cards.get(0).getRank().getPossibleValues().equals(cards.get(1).getRank().getPossibleValues())) {
			bet*=2;
			player.removeHand(hand);
			Hand hand1=new PlayerHand(player,bet);
			hand1.addCard(cards.get(0));
			hand1.hit();
			player.addHand(hand1);
			Hand hand2=new PlayerHand(player,bet);
			hand2.addCard(cards.get(1));
			hand2.hit();
			player.addHand(hand2);
		} else throw new IllegalArgumentException("Hand kan niet gesplitst worden.");
	}
	public void doubleDown(Hand hand) {
		if(hand.getCards().size()>2) throw new IllegalArgumentException("Double down kan alleen als eerste beurt!");
		bet*=2;
		hit();
		stand();
	}
}
