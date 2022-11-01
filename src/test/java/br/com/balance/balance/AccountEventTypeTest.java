package br.com.balance.balance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.balance.balance.domain.AccountEventType;
import br.com.balance.balance.exceptions.EventNotFoundException;


class AccountEventTypeTest {

	@Test
	void testEventTypeGet() {
		
		for(AccountEventType eventType: AccountEventType.values()) {
			assertEquals(eventType, AccountEventType.get(eventType.getCode()));
			assertEquals(eventType, AccountEventType.get(eventType.getCode().toLowerCase()));
			assertEquals(eventType, AccountEventType.get(eventType.getCode().toUpperCase()));
		}
		
		assertThrows(EventNotFoundException.class, () -> AccountEventType.get("EVENTO nAO EXISTE" + UUID.randomUUID().toString()));
		
	}	


}
