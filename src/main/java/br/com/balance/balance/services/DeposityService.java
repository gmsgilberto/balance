package br.com.balance.balance.services;

import java.math.BigDecimal;

import br.com.balance.balance.domain.Account;

/**
 * @author giba
 */
public interface DeposityService {
	
	
	/**
	 * @param accountId
	 * @param amount
	 * @return
	 */
	Account deposit(Integer accountId, BigDecimal amount);
	
}
