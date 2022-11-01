package br.com.balance.balance.services.impl;

import java.math.BigDecimal;

import br.com.balance.balance.domain.dtos.TransferResult;
import br.com.balance.balance.infra.repository.AccountRepository;
import br.com.balance.balance.services.DeposityService;
import br.com.balance.balance.services.TransferService;
import br.com.balance.balance.services.WithdrawService;
import lombok.AllArgsConstructor;

/**
 * @author giba
 */
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {
	
	private AccountRepository accountRepository;
	private DeposityService deposityService;
	private WithdrawService withdrawService;
	
	@Override
	public TransferResult transfer(Integer from, Integer to, BigDecimal amount) {
		var origin = this.accountRepository.findByAccountId(from);
		
		if(origin != null) {
			origin = this.withdrawService.withdraw(from, amount);
			var destination = this.deposityService.deposit(to, amount);
			return new TransferResult(origin, destination);
		}
		return null;
	}
	
}
