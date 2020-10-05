package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.application.strategies.HitStrategy;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.security.application.UserService;
import nl.hu.bep2.casino.security.data.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HitTest {
	@Autowired
	private BlackjackService blackjackService;
	@Autowired
	private UserService userService;
	@Autowired
	private HitStrategy hitStrategy;
	@Test
	void hitTest() {
		User user;
		try {
			user=userService.loadUserByUsername("admin");
		} catch(Exception e) {
			userService.register("admin","admin","ad","min");
			user=userService.loadUserByUsername("admin");
		}
		PlayTable table1= blackjackService.newTable(2,user);
		PlayTable table2= blackjackService.getTable(table1.getId());
		assertEquals(table1,table2,"newtable/table get werkt niet.");
		blackjackService.executeAction(blackjackService.getTable(table2.getId()).getPlayerHand(), hitStrategy);
	}
}
