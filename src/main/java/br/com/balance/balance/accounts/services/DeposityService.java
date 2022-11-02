package br.com.balance.balance.accounts.services;

import java.math.BigDecimal;

import br.com.balance.balance.accounts.Account;
import br.com.balance.balance.accounts.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author giba
 */
@AllArgsConstructor
@Slf4j
public class DeposityService {
	
	private AccountRepository repository;
	private AccountService accountService;
	
	public Account deposit(Integer accountId, BigDecimal amount) {
		var account = this.repository.findByAccountId(accountId);
		if(account == null) {
			log.info(String.format("Create new account with id %s; initial balance %s", accountId, amount));
			return this.accountService.createNewAccount(new Account(accountId, amount));
		}
		return this.repository.update(account.deposity(amount));
	}
	
}
