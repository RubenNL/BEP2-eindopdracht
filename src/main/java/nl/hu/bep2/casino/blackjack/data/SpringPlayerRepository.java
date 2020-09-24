package nl.hu.bep2.casino.blackjack.data;

import nl.hu.bep2.casino.blackjack.domain.Hand;
import nl.hu.bep2.casino.blackjack.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringPlayerRepository extends JpaRepository<Player,Integer> {
	Optional<Player> findByHand(Hand hand);
}
