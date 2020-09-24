package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Table {
	@Id
	private Integer id;
	@OneToOne
	private Shoe shoe;
	@OneToOne
	private HumanPlayer player;
	@OneToOne
	private Dealer dealer;

	public Table() {}
	public Table(Shoe shoe) {
		this.shoe=shoe;
		this.dealer=new Dealer(this);
	}
	public void setPlayer(HumanPlayer player) {
		if(this.player!=null) throw new UnsupportedOperationException("player al defined!");
		this.player=player;
	}
	public Shoe getShoe() {
		return this.shoe;
	}
	public Dealer getDealer() {return this.dealer;}

	public HumanPlayer getPlayer() {return this.player;}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	public Integer getId() {
		return id;
	}
}
