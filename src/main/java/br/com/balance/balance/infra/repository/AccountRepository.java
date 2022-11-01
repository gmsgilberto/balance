package br.com.balance.balance.infra.repository;

import br.com.balance.balance.domain.Account;

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
