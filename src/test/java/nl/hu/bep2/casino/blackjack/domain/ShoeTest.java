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
		Card firstShoeGrab=new Shoe(0,shoe1.getSeed(),0).grabCard();
		assertEquals(firstGrab,firstShoeGrab);
		Card secondShoeGrab=new Shoe(1,shoe1.getSeed(),1).grabCard();
		assertEquals(secondGrab,secondShoeGrab,"zelfde kaart van verschillende shoes zou hetzelfde moeten zijn.");
	}
}