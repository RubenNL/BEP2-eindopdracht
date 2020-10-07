package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.domain.PlayTable;

public class TableDTO {
	public final HandDTO player;
	public final HandDTO dealer;
	public final Long bet;
	public final long tableId;
	public final ShoeDTO shoe;
	public TableDTO(PlayTable table) {
		this.player=new HandDTO(table.getPlayerHand(),true);
		this.dealer=new HandDTO(table.getDealerHand(),player.state!=HandStates.PLAYING);
		this.shoe=new ShoeDTO(table.getShoe());
		this.tableId=table.getId();
		this.bet=table.getBet();
	}
}
