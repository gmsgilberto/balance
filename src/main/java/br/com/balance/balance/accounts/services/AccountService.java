package br.com.balance.balance.accounts.services;

import br.com.balance.balance.accounts.Account;
import br.com.balance.balance.accounts.exceptions.DuplicateAccountException;
import br.com.balance.balance.accounts.repository.AccountRepository;
import lombok.AllArgsConstructor;

/**
 * @author giba
 */
@AllArgsConstructor
public class AccountService {
	
	private AccountRepository repository;
	
	public Account createNewAccount(Account newAccount) {
		if(this.repository.findByAccountId(newAccount.getId()) != null) {
			throw new DuplicateAccountException(newAccount.getId());
		}
		return this.repository.store(newAccount);
	}
	
}
