package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.application.BlackjackService;
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

	public BlackjackController(BlackjackService blackjackService, UserService userService) {
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
}
