package br.com.balance.balance.infra.repository.impl;

import java.util.HashMap;
import java.util.Map;

import br.com.balance.balance.domain.Account;
import br.com.balance.balance.infra.repository.AccountRepository;
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
			log.info("Reset database");
			cache.clear();
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
			log.info("Store new account %s", newAccount);
			cache.put(newAccount.getId(), new Account(newAccount));
			return newAccount;
		}
	}
	
	@Override
	public Account update(Account account) {
		synchronized (cache) {
			log.info("Update account %s", account);
			cache.put(account.getId(), new Account(account) );
			return account;
		}
	}
	
	
}
