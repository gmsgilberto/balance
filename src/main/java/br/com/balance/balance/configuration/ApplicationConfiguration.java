package br.com.balance.balance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.balance.balance.accounts.repository.AccountRepository;
import br.com.balance.balance.accounts.repository.impl.AccountRepositoryInMemory;
import br.com.balance.balance.accounts.services.AccountService;
import br.com.balance.balance.accounts.services.DeposityService;
import br.com.balance.balance.accounts.services.TransferService;
import br.com.balance.balance.accounts.services.WithdrawService;

/**
 * @author giba
 */
@Configuration
public class ApplicationConfiguration {

	@Bean
	public TransferService transferService( AccountRepository repository, 
											DeposityService deposityService, 
											WithdrawService withdrawService) {
		
		return new TransferService(repository, deposityService, withdrawService);
	}

	@Bean
	public DeposityService deposityService(	AccountRepository repository, 
											AccountService accountService) {
		return new DeposityService(repository,accountService);
	}
	
	@Bean
	public WithdrawService withdrawService(AccountRepository repository) {
		return new WithdrawService(repository);
	}
	
	@Bean
	public AccountRepository accountRepository() {
		return new AccountRepositoryInMemory();
	}
	
	@Bean
	public AccountService accountService(AccountRepository accountRepository) {
		return new AccountService(accountRepository);
	}

}
