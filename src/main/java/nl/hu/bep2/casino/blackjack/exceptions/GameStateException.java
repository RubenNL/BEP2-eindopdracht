package nl.hu.bep2.casino.blackjack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class GameStateException extends RuntimeException {
	public GameStateException(String message) {super(message);}
}
