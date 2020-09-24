package nl.hu.bep2.casino.blackjack.data;

import nl.hu.bep2.casino.blackjack.domain.Shoe;
import nl.hu.bep2.casino.blackjack.domain.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringShoeRepository extends JpaRepository<Shoe, Integer> {
	Optional<Shoe> findByTable(Table table);
}
