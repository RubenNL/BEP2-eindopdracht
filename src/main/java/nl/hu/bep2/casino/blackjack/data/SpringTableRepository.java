package nl.hu.bep2.casino.blackjack.data;

import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.blackjack.domain.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringTableRepository extends JpaRepository<Table,Integer> {
	Optional<Table> findByPlayer(Player player);
}
