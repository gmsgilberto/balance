package br.com.balance.balance.services.impl;

import java.math.BigDecimal;

import br.com.balance.balance.domain.Account;
import br.com.balance.balance.infra.repository.AccountRepository;
import br.com.balance.balance.services.WithdrawService;
import lombok.AllArgsConstructor;

/**
 * @author giba
 */
@AllArgsConstructor
public class WithdrawServiceImpl implements WithdrawService {
	
	private AccountRepository repository;
	
	@Override
	public Account withdraw(Integer accountId, BigDecimal amount) {
		var account = this.repository.findByAccountId(accountId);
		if(account == null) {
			return null;
		}
		return this.repository.update(account.withdraw(amount));
	}
	
}
