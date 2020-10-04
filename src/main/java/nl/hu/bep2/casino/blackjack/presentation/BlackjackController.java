package nl.hu.bep2.casino.blackjack.presentation;

import nl.hu.bep2.casino.blackjack.application.BlackjackService;
import nl.hu.bep2.casino.blackjack.domain.PlayTable;
import nl.hu.bep2.casino.security.application.UserService;
import nl.hu.bep2.casino.security.data.User;
import nl.hu.bep2.casino.security.data.UserProfile;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public PlayTable createGame(Authentication authentication) {
		User user = userService.loadUserByUsername(((UserProfile) authentication.getPrincipal()).getUsername());
		return blackjackService.newTable(2,user);
	}
}
