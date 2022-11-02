package br.com.balance.balance.accounts;

import java.math.BigDecimal;

import lombok.Getter;

/**
 * @author giba
 */
final class DeposityStrategy{

	@Getter
	private static final DeposityStrategy instance = new DeposityStrategy();
	
	private DeposityStrategy() {
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
