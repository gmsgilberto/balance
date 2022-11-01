package br.com.balance.balance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.balance.balance.infra.repository.AccountRepository;
import br.com.balance.balance.infra.repository.impl.AccountRepositoryInMemory;
import br.com.balance.balance.services.AccountService;
import br.com.balance.balance.services.DeposityService;
import br.com.balance.balance.services.TransferService;
import br.com.balance.balance.services.WithdrawService;
import br.com.balance.balance.services.impl.AccountServiceImpl;
import br.com.balance.balance.services.impl.DeposityServiceImpl;
import br.com.balance.balance.services.impl.TransferServiceImpl;
import br.com.balance.balance.services.impl.WithdrawServiceImpl;

/**
 * @author giba
 */
@Configuration
public class ApplicationConfiguration {

	@Bean
	public TransferService transferService( AccountRepository repository, 
											DeposityService deposityService, 
											WithdrawService withdrawService) {
		
		return new TransferServiceImpl(repository, deposityService, withdrawService);
	}

	@Bean
	public DeposityService deposityService(	AccountRepository repository, 
											AccountService accountService) {
		return new DeposityServiceImpl(repository,accountService);
	}
	
	@Bean
	public WithdrawService withdrawService(AccountRepository repository) {
		return new WithdrawServiceImpl(repository);
	}
	
	@Bean
	public AccountRepository accountRepository() {
		return new AccountRepositoryInMemory();
	}
	
	@Bean
	public AccountService accountService(AccountRepository accountRepository) {
		return new AccountServiceImpl(accountRepository);
	}

}
