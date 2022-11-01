package br.com.balance.balance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.balance.balance.infra.repository.AccountRepository;
import br.com.balance.balance.infra.repository.impl.AccountRepositoryInMemory;
import br.com.balance.balance.services.AccountService;
import br.com.balance.balance.services.impl.AccountServiceImpl;

/**
 * @author giba
 */
@Configuration
public class ApplicationConfiguration {

	@Bean
	public AccountRepository accountRepository() {
		return new AccountRepositoryInMemory();
	}
	
	@Bean
	public AccountService accountService(AccountRepository accountRepository) {
		return new AccountServiceImpl(accountRepository);
	}

}
