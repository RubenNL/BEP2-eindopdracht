package nl.hu.bep2.casino.blackjack.presentation;

import javax.validation.constraints.Positive;

public class Bet {
	@Positive
	public Long bet;
}
