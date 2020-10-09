package nl.hu.bep2.casino.blackjack.domain;

import java.util.*;

public enum Rank {
	A("Ace", Arrays.asList(1,11)),
	TWO("2", Collections.singletonList(2)),
	THREE("3", Collections.singletonList(3)),
	FOUR("4", Collections.singletonList(4)),
	FIVE("5", Collections.singletonList(5)),
	SIX("6", Collections.singletonList(6)),
	SEVEN("7", Collections.singletonList(7)),
	EIGHT("8", Collections.singletonList(8)),
	NINE("9", Collections.singletonList(9)),
	TEN("10", Collections.singletonList(10)),
	J("Jack", Collections.singletonList(10)),
	Q("Queen", Collections.singletonList(10)),
	K("King", Collections.singletonList(10));
	public final String name;
	public final List<Integer> possibleValues;
	Rank(String name, List<Integer> possibleValues) {
		this.name=name;
		this.possibleValues=possibleValues;
	}
}
