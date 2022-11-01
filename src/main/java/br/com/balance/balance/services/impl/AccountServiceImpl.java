package br.com.balance.balance.services.impl;

import java.math.BigDecimal;

import br.com.balance.balance.domain.Account;
import br.com.balance.balance.exceptions.DuplicateAccountException;
import br.com.balance.balance.infra.repository.AccountRepository;
import br.com.balance.balance.services.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author giba
 */
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository repository;
	
	
	@Override
	public Account createNewAccount(Account newAccount) {
		if(this.repository.findByAccountId(newAccount.getId()) != null) {
			throw new DuplicateAccountException(newAccount.getId());
		}
		return this.repository.store(newAccount);
	}
	
	
	@Override
	public Account deposit(Integer accountId, BigDecimal amount) {
		var account = this.repository.findByAccountId(accountId);
		if(account == null) {
			log.info("Create new account with id %s; initial amount %s", amount);
			return this.createNewAccount(new Account(accountId, amount));
		}
		return this.repository.update(account.deposity(amount));
	}
	
	
	@Override
	public Account withdraw(Integer accountId, BigDecimal amount) {
		var account = this.repository.findByAccountId(accountId);
		if(account == null) {
			return null;
		}
		return this.repository.update(account.withdraw(amount));
	}
	
	@Override
	public Account transfer(Integer from, Integer to, BigDecimal amount) {
		var origin = this.repository.findByAccountId(from);
		if(origin != null) {
			origin = this.withdraw(from, amount);
			this.deposit(to, amount);
		}
		return origin;
	}
	
}
