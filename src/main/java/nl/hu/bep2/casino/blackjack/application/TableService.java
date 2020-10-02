package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.data.SpringPlayerRepository;
import nl.hu.bep2.casino.blackjack.data.SpringShoeRepository;
import nl.hu.bep2.casino.blackjack.data.SpringTableRepository;
import nl.hu.bep2.casino.blackjack.domain.HumanPlayer;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.blackjack.domain.Shoe;
import nl.hu.bep2.casino.security.data.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class TableService {
	private final SpringTableRepository tableRepository;
	private final SpringPlayerRepository playerRepository;
	private final SpringShoeRepository shoeRepository;
	public TableService(SpringTableRepository tableRepository, SpringPlayerRepository playerRepository,SpringShoeRepository shoeRepository) {
		this.tableRepository = tableRepository;
		this.playerRepository=playerRepository;
		this.shoeRepository=shoeRepository;
	}
	private PlayTable firstJoin(int decks,User user) {
		PlayTable table = new PlayTable(new Shoe(decks));
		Player player=new HumanPlayer(user, table);
		table.setPlayer(player);
		tableRepository.save(table);
		return table;
	}
	private boolean hasTable(User user) {
		return playerRepository.findByUser(user).isPresent();
	}
	public PlayTable newTable(int decks, User user) {
		if(!hasTable(user)) return firstJoin(decks,user);
		PlayTable table=getTable(user);
		shoeRepository.findByTable(table).get().reset();
		table.reset();
		tableRepository.save(table);
		return table;
	}
	public PlayTable getTable(User user) {
		return tableRepository.findByPlayer(playerRepository.findByUser(user).get()).get();
	}
}
