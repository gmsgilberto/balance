package br.com.balance.balance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.com.balance.balance.controllers.AccountEventHub;
import br.com.balance.balance.controllers.BalanceController;
import br.com.balance.balance.controllers.AdminController;
import br.com.balance.balance.controllers.dtos.AccountEventRequest;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ApplicationTest {

	@Autowired
	private AdminController adminController;
	
	@Autowired
	private AccountEventHub accountEventController;
	
	@Autowired
	private BalanceController balanceController;
	
	@Test
	void fullTest() {
		reset();
		getBalanceFromNoExitingAccount();
		createAccountWithInitialBalance();
		depositIntoExistingAccount();
		getBalanceForExistingAccount();
		withdrawFromNonExistingAccount();
		withdrawFromExistingAccount();
		transferFromExistingAccount();
		transferFromNExistingAccount();
	}

	private void reset() {
		log.info("# Reset state before starting tests");
		ResponseEntity<String> resetResponse = adminController.reset(); 
		assertNotNull(resetResponse);
		assertNotNull(resetResponse.getBody());
		assertEquals(200, resetResponse.getStatusCodeValue());
		assertEquals("OK", resetResponse.getBody());
	}
	
	private void getBalanceFromNoExitingAccount() {
		log.info("# Get balance for non-existing account");
		var response = balanceController.getBalance(1234);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(404, response.getStatusCodeValue());
		assertEquals(BigDecimal.ZERO, response.getBody());
	}
	
	
	private void createAccountWithInitialBalance() {
		log.info("# Create account with initial balance");
		var event = new AccountEventRequest();
		event.setType("deposit");
		event.setDestination(100);
		event.setAmount(new BigDecimal(10));
		var response = accountEventController.event(event);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(201, response.getStatusCodeValue());
		assertEquals("{\"destination\": {\"id\":\"100\", \"balance\":10}}", response.getBody());
	}
	
	
	private void depositIntoExistingAccount() {
		log.info("# Deposit into existing account");
		var event = new AccountEventRequest();
		event.setType("deposit");
		event.setDestination(100);
		event.setAmount(new BigDecimal(10));
		var response = accountEventController.event(event);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(201, response.getStatusCodeValue());
		assertEquals("{\"destination\": {\"id\":\"100\", \"balance\":20}}", response.getBody());
	}
	
	private void getBalanceForExistingAccount() {
		log.info("# Get balance for existing account");
		var response = balanceController.getBalance(100);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(new BigDecimal("20"), response.getBody());
	}
	
	private void withdrawFromNonExistingAccount() {
		log.info("# Withdraw from non-existing account");
		var event = new AccountEventRequest();
		event.setType("withdraw");
		event.setOrigin(200);
		event.setAmount(new BigDecimal(10));
		var response = accountEventController.event(event);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(404, response.getStatusCodeValue());
		assertEquals("0", response.getBody());
	}
	
	
	
	private void withdrawFromExistingAccount() {
		log.info("# Withdraw from existing account");
		var event = new AccountEventRequest();
		event.setType("withdraw");
		event.setOrigin(100);
		event.setAmount(new BigDecimal(5));
		var response = accountEventController.event(event);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(201, response.getStatusCodeValue());
		assertEquals("{\"origin\": {\"id\":\"100\", \"balance\":15}}", response.getBody());
	}
	
	
	private void transferFromExistingAccount() {
		log.info("# Transfer from existing account");
		var event = new AccountEventRequest();
		event.setType("transfer");
		event.setOrigin(100);
		event.setAmount(new BigDecimal(15));
		event.setDestination(300);
		
		var response = accountEventController.event(event);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(201, response.getStatusCodeValue());
		assertEquals("{\"origin\": {\"id\":\"100\", \"balance\":0}, \"destination\": {\"id\":\"300\", \"balance\":15}}", response.getBody());
	}	

	
	private void transferFromNExistingAccount() {
		log.info("# Transfer from non-existing account");
		var event = new AccountEventRequest();
		event.setType("transfer");
		event.setOrigin(200);
		event.setAmount(new BigDecimal(15));
		event.setDestination(300);
		
		var response = accountEventController.event(event);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(404, response.getStatusCodeValue());
		assertEquals("0", response.getBody());
	}	


}
