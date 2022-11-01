package br.com.balance.balance.domain;

import java.math.BigDecimal;

import lombok.Getter;

/**
 * @author giba
 */
final class AccountDeposityStrategy{

	@Getter
	private static final AccountDeposityStrategy instance = new AccountDeposityStrategy();
	
	private AccountDeposityStrategy() {
		super();
	}
	
	public Account execute(Account account, BigDecimal amount) {
		if(amount != null) {
			var newBalance = account.getBalance().add(amount);
			account = new Account(account, newBalance);
		}
		return account;
	}
	
}
