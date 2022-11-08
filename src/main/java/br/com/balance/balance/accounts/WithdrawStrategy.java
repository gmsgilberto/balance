package br.com.balance.balance.accounts;

import java.math.BigDecimal;

import br.com.balance.balance.accounts.exceptions.SaldoInsuficienteException;
import lombok.Getter;

/**
 * @author giba
 */
final class WithdrawStrategy{

	@Getter
	private static final WithdrawStrategy instance = new WithdrawStrategy();
	
	private WithdrawStrategy() {
		super();
	}
	
	public Account execute(Account account, BigDecimal amount) {
		if(amount != null) {
			var newBalance = account.getBalance().subtract(amount);
			
			if(newBalance.doubleValue() < 0.0) {
				throw new SaldoInsuficienteException();
			}
			
			account = new Account(account, newBalance);
		}
		return account;
	}
	
}
