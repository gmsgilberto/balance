package br.com.balance.balance.services;

import java.math.BigDecimal;

import br.com.balance.balance.domain.Account;

public interface AccountService {

	Account createNewAccount(Account account);
	
	Account deposit(Integer accountId, BigDecimal amount);
	
	Account withdraw(Integer accountId, BigDecimal amount);
	
	Account transfer(Integer from, Integer to, BigDecimal amount);
	
}
