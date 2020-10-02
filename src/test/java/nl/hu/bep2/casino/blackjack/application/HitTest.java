package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.application.strategies.HitStrategy;
import nl.hu.bep2.casino.blackjack.data.SpringPlayerRepository;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.blackjack.domain.Player;
import nl.hu.bep2.casino.security.application.UserService;
import nl.hu.bep2.casino.security.data.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HitTest {
	@Before
	void createAdmin() {
		userService.register("admin","admin","ad","min");
	}
	@Autowired
	private TableService tableService;
	@Autowired
	private UserService userService;
	@Autowired
	private HandService handService;
	@Autowired
	private SpringPlayerRepository playerRepository;
	@Autowired
	private HitStrategy hitStrategy;
	@Test
	void hitTest() {
		User user=userService.loadUserByUsername("admin");
		PlayTable table1=tableService.newTable(2,user);
		PlayTable table2=tableService.getTable(user);
		assertEquals(table1,table2,"newtable/table get werkt niet.");
		Player player=playerRepository.findByUser(user).get();
		System.out.println(player);
		System.out.println(player.getId());
		System.out.println(player.getHand());
		handService.executeAction(player, hitStrategy);
		assertEquals(1,playerRepository.findByUser(user).get().getHand().getCards().size());
		assertEquals(1,playerRepository.findByUser(user).get().getTable().getShoe().getCardsTaken());
	}
}
