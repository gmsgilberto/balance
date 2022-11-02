package br.com.balance.balance.accounts.exceptions;

public class AccountNotfoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AccountNotfoundException(Integer accountId) {
		super(String.format("Account not found: %s", accountId));
	}

}
