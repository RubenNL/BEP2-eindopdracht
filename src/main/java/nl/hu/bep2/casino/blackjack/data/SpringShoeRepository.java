package nl.hu.bep2.casino.blackjack.data;

import nl.hu.bep2.casino.blackjack.domain.Shoe;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringShoeRepository extends JpaRepository<Shoe, Integer> {
	Optional<Shoe> findByTable(PlayTable playTable);
}
