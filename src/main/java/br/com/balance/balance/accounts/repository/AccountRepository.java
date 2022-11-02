package br.com.balance.balance.accounts.repository;

import br.com.balance.balance.accounts.Account;

/**
 * @author giba
 */
public interface AccountRepository {

	/**
	 * Clear database
	 * @return
	 */
	void reset();

	Account findByAccountId(Integer accountId);

	Account store(Account newAccount);

	Account update(Account account);
	
}
