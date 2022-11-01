package br.com.balance.balance.domain;

import java.math.BigDecimal;

import lombok.Getter;

/**
 * @author giba
 */
final class AccountWithdrawStrategy{

	@Getter
	private static final AccountWithdrawStrategy instance = new AccountWithdrawStrategy();
	
	private AccountWithdrawStrategy() {
		super();
	}
	
	public Account execute(Account account, BigDecimal amount) {
		if(amount != null) {
			var newBalance = account.getBalance().subtract(amount);
			account = new Account(account, newBalance);
		}
		return account;
	}
	
}
