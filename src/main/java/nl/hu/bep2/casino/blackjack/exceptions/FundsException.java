package nl.hu.bep2.casino.blackjack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
public class FundsException extends RuntimeException {
	public FundsException(String message) {super(message);}
}
