package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.security.data.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PlayTable {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne(cascade=CascadeType.ALL)
	@MapsId
	private Shoe shoe;
	@OneToOne(cascade=CascadeType.ALL)
	private Hand playerHand;
	@OneToOne(cascade=CascadeType.ALL)
	private Hand dealerHand;
	@OneToOne(cascade=CascadeType.MERGE)
	private User user;
	private Long bet;
	public PlayTable() {}
	public PlayTable(Shoe shoe) {
		this.shoe=shoe;
		this.dealerHand=new Hand(this);
		this.playerHand=new Hand(this);
	}
	public void setUser(User user) {
		this.user=user;
	}
	public User getUser() {return this.user;}
	public Shoe getShoe() {
		return this.shoe;
	}
	public Hand getDealerHand() {return this.dealerHand;}

	public Hand getPlayerHand() {return this.playerHand;}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setBet(Long bet) {
		this.bet=bet;
	}
	public Long getBet() {
		return this.bet;
	}
	public void doubleBet() {
		this.bet *= 2;
	}
	public void reset() {
		this.shoe.reset();
		this.dealerHand.reset();
		this.playerHand.reset();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PlayTable playTable = (PlayTable) o;
		return id.equals(playTable.id) &&
				shoe.equals(playTable.shoe) &&
				bet == playTable.bet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, shoe,bet);
	}

}
