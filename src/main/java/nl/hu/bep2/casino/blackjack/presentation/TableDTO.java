package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.domain.PlayTable;

public class TableDTO {
	public final HandDTO player;
	public final HandDTO dealer;
	public TableDTO(PlayTable table) {
		player=new HandDTO(table.getPlayerHand(),true);
		dealer=new HandDTO(table.getDealerHand(),table.isPlayerFinished());
	}
}
