package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.application.strategies.HitStrategy;
import nl.hu.bep2.casino.blackjack.data.SpringTableRepository;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.chips.data.SpringChipsRepository;
import nl.hu.bep2.casino.security.application.UserService;
import nl.hu.bep2.casino.security.data.SpringUserRepository;
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
	private SpringChipsRepository chipsRepository;
	@Autowired
	private SpringUserRepository userRepository;
	@Autowired
	private HitStrategy hitStrategy;
	@Autowired
	private SpringTableRepository tableRepository;
	@Test
	void hitTest() {
		try {
			User user=userService.loadUserByUsername("admin");
			try {
				chipsRepository.delete(chipsRepository.findByUser(user).get());
			} catch(Exception e) {}
			try {
				tableRepository.delete(tableRepository.findByUser(user).get());
			} catch(Exception e) {}
			userRepository.delete(user);
		} catch(Exception e) {
			e.printStackTrace();
		}
		userService.register("admin","admin","ad","min");
		User user=userService.loadUserByUsername("admin");
		PlayTable table1= blackjackService.newTable(2,user);
		PlayTable table2= blackjackService.getTable(user);
		assertEquals(table1,table2,"newtable/table get werkt niet.");
		System.out.println("blackjackservice:"+blackjackService);
		blackjackService.executeAction(
				blackjackService
						.getTable(user)
						.getPlayerHand()
				, hitStrategy);
	}
}
