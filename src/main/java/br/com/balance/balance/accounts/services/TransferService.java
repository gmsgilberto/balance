package br.com.balance.balance.accounts.services;

import java.math.BigDecimal;

import br.com.balance.balance.accounts.dto.TransferResult;
import br.com.balance.balance.accounts.exceptions.AccountNotfoundException;
import br.com.balance.balance.accounts.repository.AccountRepository;
import lombok.AllArgsConstructor;

/**
 * @author giba
 */
@AllArgsConstructor
public class TransferService {
	
	private AccountRepository accountRepository;
	private DeposityService deposityService;
	private WithdrawService withdrawService;
	
	public TransferResult transfer(Integer from, Integer to, BigDecimal amount) {
		var origin = this.accountRepository.findByAccountId(from);
		if(origin == null) {
			throw new AccountNotfoundException(from);
		}
		
		origin = this.withdrawService.withdraw(from, amount);
		var destination = this.deposityService.deposit(to, amount);
		return new TransferResult(origin, destination);
	}
	
}
