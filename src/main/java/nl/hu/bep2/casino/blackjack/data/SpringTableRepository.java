package nl.hu.bep2.casino.blackjack.data;

import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.security.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringTableRepository extends JpaRepository<PlayTable,Long> {
	Optional<PlayTable> findByUser(User user);
}
