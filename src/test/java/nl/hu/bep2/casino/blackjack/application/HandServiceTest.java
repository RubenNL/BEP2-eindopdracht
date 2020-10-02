package nl.hu.bep2.casino.blackjack.application;

import nl.hu.bep2.casino.blackjack.data.SpringHandRepository;
import nl.hu.bep2.casino.blackjack.domain.Hand;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class HandServiceTest {
	@Autowired
	private SpringHandRepository handRepository;
	@Autowired
	private HandService handService;

	@Test
	public void persistanceTest() {
		Hand hand=handService.newHand();
		System.out.println("hand1:"+hand.toString()+", id:"+hand.getId());
		Hand hand2 = handRepository.findById(hand.getId()).get();
		System.out.println("hand2:"+hand2.toString()+", id:"+hand2.getId());
		assertEquals(hand.getId(),hand2.getId(),"Beide handen zouden hetzelfde ID moeten hebben.");
		handRepository.deleteById(hand.getId());
		assertTrue(handRepository.findById(hand.getId()).isEmpty(),"nu zou hij verwijderd moeten zijn.");
	}
}