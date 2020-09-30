package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.*;

@Entity
@Table(name="tables")
public class PlayTable {
	@Id
	private Integer id;
	@OneToOne
	@MapsId
	private Shoe shoe;
	@OneToOne
	@MapsId
	private HumanPlayer player;
	@OneToOne
	private Dealer dealer;

	public PlayTable() {}
	public PlayTable(Shoe shoe) {
		this.shoe=shoe;
//		this.dealer=new Dealer(this);
	}
	public void setPlayer(HumanPlayer player) {
//		if(this.player!=null) throw new UnsupportedOperationException("player al defined!");
//		this.player=player;
	}
	public Shoe getShoe() {
		return this.shoe;
	}
//	public Dealer getDealer() {return this.dealer;}

//	public HumanPlayer getPlayer() {return this.player;}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
