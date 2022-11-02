package br.com.balance.balance.accounts.exceptions;

public class EventNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EventNotFoundException(String code) {
		super(String.format("there is no event type %s", code));
	}
	
}
