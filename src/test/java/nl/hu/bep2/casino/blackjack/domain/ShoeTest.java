package nl.hu.bep2.casino.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoeTest {
	@Test
	void testRandom() {
		Shoe shoe1=new Shoe(1);
		System.out.println(shoe1);
		Card firstGrab=shoe1.grabCard();
		Card secondGrab=shoe1.grabCard();
		assertNotEquals(firstGrab,secondGrab,"kaarten kunnen niet hetzelfde zijn.");
		Card firstShoeGrab=new Shoe(1,shoe1.getSeed(),0).grabCard();
		assertEquals(firstGrab,firstShoeGrab);
		Card secondShoeGrab=new Shoe(1,shoe1.getSeed(),1).grabCard();
		assertEquals(secondGrab,secondShoeGrab,"zelfde kaart van verschillende shoes zou hetzelfde moeten zijn.");
	}
	@Test
	void testShoeSize() {
		assertEquals(0,new Shoe(0).cardsAvailable(),"0 decks zou geen kaarten moeten zijn.");
		assertEquals(52,new Shoe(1).cardsAvailable(),"1 deck zou 52 kaarten moeten zijn.");
		assertEquals(104,new Shoe(2).cardsAvailable(),"2 decks zou 104 kaarten moeten zijn.");
		assertEquals(156,new Shoe(3).cardsAvailable(),"3 decks zou 156 kaarten moeten zijn.");
	}
	@Test
	void testCardsTaken() {
		Shoe shoe=new Shoe(2);
		assertEquals(0,shoe.getCardsTaken(),"Nog geen kaarten gepakt");
		shoe.grabCard();
		assertEquals(1,shoe.getCardsTaken(),"Nu 1 kaart gepakt.");
		shoe.grabCard();
		assertEquals(2,shoe.getCardsTaken(),"Nu 2 kaart gepakt.");
	}
}