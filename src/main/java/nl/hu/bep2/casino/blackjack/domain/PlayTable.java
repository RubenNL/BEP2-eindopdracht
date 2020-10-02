package nl.hu.bep2.casino.blackjack.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tables")
public class PlayTable {
	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne
	@MapsId
	private Shoe shoe;
	@OneToOne(cascade=CascadeType.ALL)
	private Player player;
	@OneToOne(cascade=CascadeType.ALL)
	private Player dealer;

	public PlayTable() {}
	public PlayTable(Shoe shoe) {
		this.shoe=shoe;
		this.dealer=new Player(this);
	}
	public void setPlayer(Player player) {
		if(this.player!=null) throw new UnsupportedOperationException("player al defined!");
		this.player=player;
	}
	public Shoe getShoe() {
		return this.shoe;
	}
	public Player getDealer() {return this.dealer;}

	public Player getPlayer() {return this.player;}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void reset() {
		this.shoe.reset();
		this.dealer.reset();
		this.player.reset();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PlayTable playTable = (PlayTable) o;
		return id.equals(playTable.id) &&
				shoe.equals(playTable.shoe);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, shoe);
	}
}
