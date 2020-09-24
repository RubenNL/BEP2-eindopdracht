package nl.hu.bep2.casino.blackjack.domain;

import java.util.*;

public enum Rank {
	A("Ace", Arrays.asList(1,11)),
	TWO("2", Arrays.asList(2)),
	THREE("3", Arrays.asList(3)),
	FOUR("4", Arrays.asList(4)),
	FIVE("5", Arrays.asList(5)),
	SIX("6", Arrays.asList(6)),
	SEVEN("7", Arrays.asList(7)),
	EIGHT("8", Arrays.asList(8)),
	NINE("9", Arrays.asList(9)),
	TEN("10", Arrays.asList(10)),
	J("Jack", Arrays.asList(10)),
	Q("Queen", Arrays.asList(10)),
	K("King", Arrays.asList(10));
	public final String name;
	public final List<Integer> possibleValues;
	private Rank(String name, List<Integer> possibleValues) {
		this.name=name;
		this.possibleValues=possibleValues;
	}
}
