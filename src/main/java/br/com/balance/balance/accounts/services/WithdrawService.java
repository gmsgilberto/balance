package br.com.balance.balance.accounts.services;

import java.math.BigDecimal;

import br.com.balance.balance.accounts.Account;
import br.com.balance.balance.accounts.exceptions.AccountNotfoundException;
import br.com.balance.balance.accounts.repository.AccountRepository;
import lombok.AllArgsConstructor;

/**
 * @author giba
 */
@AllArgsConstructor
public class WithdrawService  {
	
	private AccountRepository repository;
	
	public Account withdraw(Integer accountId, BigDecimal amount) {
		var account = this.repository.findByAccountId(accountId);
		if(account == null) {
			throw new AccountNotfoundException(accountId);
		}
		return this.repository.update(account.withdraw(amount));
	}
	
}
