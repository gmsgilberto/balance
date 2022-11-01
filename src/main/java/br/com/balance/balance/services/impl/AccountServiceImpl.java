package br.com.balance.balance.services.impl;

import br.com.balance.balance.domain.Account;
import br.com.balance.balance.exceptions.DuplicateAccountException;
import br.com.balance.balance.infra.repository.AccountRepository;
import br.com.balance.balance.services.AccountService;
import lombok.AllArgsConstructor;

/**
 * @author giba
 */
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository repository;
	
	
	@Override
	public Account createNewAccount(Account newAccount) {
		if(this.repository.findByAccountId(newAccount.getId()) != null) {
			throw new DuplicateAccountException(newAccount.getId());
		}
		return this.repository.store(newAccount);
	}
	
}
