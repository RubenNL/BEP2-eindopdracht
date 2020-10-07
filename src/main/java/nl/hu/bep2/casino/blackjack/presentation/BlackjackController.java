package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.application.BlackjackService;
import nl.hu.bep2.casino.blackjack.application.strategies.HitStrategy;
import nl.hu.bep2.casino.security.application.UserService;
import nl.hu.bep2.casino.security.data.User;
import nl.hu.bep2.casino.security.data.UserProfile;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blackjack")
public class BlackjackController {
	private final BlackjackService blackjackService;
	private final UserService userService;
	private final HitStrategy hitStrategy;
	public BlackjackController(BlackjackService blackjackService, UserService userService,HitStrategy hitStrategy) {
		this.hitStrategy=hitStrategy;
		this.blackjackService = blackjackService;
		this.userService=userService;
	}

	@PostMapping
	public TableDTO createGame(Authentication authentication) {
		User user = userService.loadUserByUsername(((UserProfile) authentication.getPrincipal()).getUsername());
		return new TableDTO(blackjackService.newTable(2,user));
	}
	@GetMapping(value="/{id}")
	public TableDTO getTable(@PathVariable long id) {
		return new TableDTO(blackjackService.getTable(id));
	}
	@DeleteMapping(value="/{id}")
	public void deleteTable(@PathVariable long id) {
		blackjackService.deleteTable(id);
	}
	@PostMapping(value="/{id}/bet")
	public void setBet(@PathVariable long id, @RequestBody Bet bet) {
		blackjackService.startRound(id,bet.bet);
	}
	@PostMapping(value="/{id}/action")
	public void hit(@PathVariable long id,@RequestBody ActionBean bean) {
		blackjackService.executeAction(id,bean.getStrategy());
	}
	@PostMapping(value="/{id}/dealer")
	public void dealerStep(@PathVariable long id) {
		blackjackService.dealerStep(id);
	}
}
