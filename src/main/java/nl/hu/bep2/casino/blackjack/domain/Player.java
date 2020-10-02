package nl.hu.bep2.casino.blackjack.domain;

import nl.hu.bep2.casino.blackjack.data.SpringHandRepository;
import nl.hu.bep2.casino.security.data.User;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Player {
	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne(cascade=CascadeType.ALL)
	private PlayTable playTable;
	@OneToOne(cascade=CascadeType.ALL)
	private Hand hand;
	@OneToOne(optional = true)
	private User user;
	public Player(PlayTable playTable) {
		this.playTable=playTable;
		this.hand=new Hand();
	}
	public Player() {

	}
	public PlayTable getTable() {
		return this.playTable;
	}
	public Hand getHand() {
		return this.hand;
	};

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setUser(User user) {this.user=user;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Player player = (Player) o;
		return id.equals(player.id) &&
				playTable.equals(player.playTable) &&
				hand.equals(player.hand) &&
				Objects.equals(user, player.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, playTable, hand, user);
	}

	@Override
	public String toString() {
		return "Player{" +
				"id=" + id +
				", playTable=" + playTable +
				'}';
	}
	public void reset() {
		this.hand.reset();
	}
}
