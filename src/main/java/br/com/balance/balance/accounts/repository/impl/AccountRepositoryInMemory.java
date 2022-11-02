package br.com.balance.balance.accounts.repository.impl;

import java.util.HashMap;
import java.util.Map;

import br.com.balance.balance.accounts.Account;
import br.com.balance.balance.accounts.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Only for test
 * @author giba
 */
@Slf4j
public final class AccountRepositoryInMemory implements AccountRepository{

	private final Map<Integer, Account> cache = new HashMap<>();
	
	@Override
	public void reset() {
		synchronized (cache) {
			cache.clear();
			log.info("Database reset");
		}
	}
	
	@Override
	public Account findByAccountId(Integer accountId) {
		if(this.cache.containsKey(accountId)) {
			return new Account(this.cache.get(accountId));
		}
		return null;
	}
	
	
	@Override
	public Account store(Account newAccount) {
		synchronized (cache) {
			log.info(String.format("Store new account %s", newAccount));
			cache.put(newAccount.getId(), new Account(newAccount));
			return newAccount;
		}
	}
	
	@Override
	public Account update(Account account) {
		synchronized (cache) {
			log.info(String.format("Update account %s", account));
			cache.put(account.getId(), new Account(account) );
			return account;
		}
	}
	
	
}
