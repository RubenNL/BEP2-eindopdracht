package nl.hu.bep2.casino.blackjack.data;

import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.security.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringPlayerRepository extends JpaRepository<Player,Integer> {
	Optional<Player> findByUser(User user);
}
