package br.com.balance.balance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.balance.balance.domain.Account;
import br.com.balance.balance.exceptions.DuplicateAccountException;
import br.com.balance.balance.services.AccountService;


@SpringBootTest
class CreateAccountTest {

	
	@Autowired 
	private AccountService accountService;
	
	@Test
	void testCreateAccount() {
		
		var account = new Account(10,new BigDecimal(1000));
		account = this.accountService.createNewAccount(account);
		assertNotNull(account);
		assertNotNull(account.getBalance());
		assertNotNull(account.getId());
		assertEquals(10, account.getId());
		assertEquals(new BigDecimal(1000), account.getBalance());

		assertThrows(DuplicateAccountException.class, () -> this.accountService.createNewAccount(new Account(10,new BigDecimal(10))));

		var account2 = new Account(2,new BigDecimal(2000));
		account2 = this.accountService.createNewAccount(account2);
		assertNotNull(account2);
		assertNotNull(account2.getBalance());
		assertNotNull(account2.getId());
		assertEquals(2, account2.getId());
		assertEquals(new BigDecimal(2000), account2.getBalance());
		assertThrows(DuplicateAccountException.class, () -> this.accountService.createNewAccount(new Account(2,new BigDecimal(10))));
		
		
	}	


}
