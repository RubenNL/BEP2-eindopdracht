package nl.hu.bep2.casino.blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
	static List<Rank> ranks= Arrays.asList(Rank.values()); //wordt bij alle tests gebruikt, hoeft niet opnieuw aangemaakt te worden.
	private Hand hand;
	@BeforeEach
	void beforeEach() {
		hand=new Hand();
	}
	@Test
	void TestValueSimple() {
		hand.addCard(new Card(Faces.CLUBS,ranks.get(1)));//kaart 2, is 2
		assertEquals(Arrays.asList(2),hand.getPossibleTotalValues());
		hand.addCard(new Card(Faces.CLUBS,ranks.get(11)));//kaart 12, zou 10 moeten zijn.
		assertEquals(Arrays.asList(12),hand.getPossibleTotalValues());
	}
	@Test
	void testAce() {
		hand.addCard(new Card(Faces.CLUBS,ranks.get(0)));
		assertEquals(Arrays.asList(1,11),hand.getPossibleTotalValues());
		hand.addCard(new Card(Faces.CLUBS,ranks.get(0)));
		assertEquals(Arrays.asList(2,12,12,22),hand.getPossibleTotalValues());
	}
	@Test
	void testBlackjackSimple() {
		hand.addCard(new Card(Faces.DIAMONDS,ranks.get(9))); //10;
		assertFalse(hand.hasBlackjack(),"nu nog niet");
		hand.addCard(new Card(Faces.SPADES,ranks.get(0)));//ACE
		assertTrue(hand.hasBlackjack(),"Nu zou er wel blackjack moeten zijn.");
	}
	@Test
	void testBlackjackMoreCards() {
		hand.addCard(new Card(Faces.DIAMONDS,ranks.get(9))); //10;
		hand.addCard(new Card(Faces.DIAMONDS,ranks.get(8))); //9;
		hand.addCard(new Card(Faces.DIAMONDS,ranks.get(1))); //2;
		assertFalse(hand.hasBlackjack(),"meer dan 2 kaarten=geen blackjack");
	}
	@Test
	void testBust() {
		hand.addCard(new Card(Faces.DIAMONDS,ranks.get(12))); //KING
		hand.addCard(new Card(Faces.DIAMONDS,ranks.get(12))); //KING
		hand.addCard(new Card(Faces.DIAMONDS,ranks.get(12))); //KING
		assertTrue(hand.isBust());
	}
	@Test
	void testBustFalse() {
		hand.addCard(new Card(Faces.DIAMONDS,ranks.get(12))); //KING
		hand.addCard(new Card(Faces.DIAMONDS,ranks.get(12))); //KING
		hand.addCard(new Card(Faces.DIAMONDS,ranks.get(0))); //ACE
		assertFalse(hand.isBust());
	}
}