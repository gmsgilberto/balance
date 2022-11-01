package br.com.balance.balance.services.impl;

import java.math.BigDecimal;

import br.com.balance.balance.domain.Account;
import br.com.balance.balance.infra.repository.AccountRepository;
import br.com.balance.balance.services.AccountService;
import br.com.balance.balance.services.DeposityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author giba
 */
@AllArgsConstructor
@Slf4j
public class DeposityServiceImpl implements DeposityService {
	
	private AccountRepository repository;
	private AccountService accountService;
	
	
	@Override
	public Account deposit(Integer accountId, BigDecimal amount) {
		var account = this.repository.findByAccountId(accountId);
		if(account == null) {
			log.info(String.format("Create new account with id %s; initial balance %s", accountId, amount));
			return this.accountService.createNewAccount(new Account(accountId, amount));
		}
		return this.repository.update(account.deposity(amount));
	}
	
	
}
