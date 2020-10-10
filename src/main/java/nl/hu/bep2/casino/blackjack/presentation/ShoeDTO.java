package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.domain.Shoe;

public class ShoeDTO {
	//pattern: DTO (structual)
	public final int cardsTaken;
	public final int availableCards;
	public final int decks;
	public ShoeDTO(Shoe shoe) {
		cardsTaken=shoe.getCardsTaken();
		availableCards=shoe.getCardsAvailable();
		decks=shoe.getDecks();
	}
}
