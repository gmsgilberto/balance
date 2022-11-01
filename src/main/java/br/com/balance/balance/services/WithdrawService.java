package br.com.balance.balance.services;

import java.math.BigDecimal;

import br.com.balance.balance.domain.Account;

/**
 * @author giba
 */
public interface WithdrawService {

	
	/**
	 * @param accountId
	 * @param amount
	 * @return
	 */
	Account withdraw(Integer accountId, BigDecimal amount);
	
}
