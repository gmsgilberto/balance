package br.com.balance.balance.exceptions;

public class DuplicateAccountException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateAccountException(Integer accountId) {
		super(String.format("there is another accont with the same id %s", accountId));
	}
	
}
