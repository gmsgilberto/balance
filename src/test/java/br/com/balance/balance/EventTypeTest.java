package br.com.balance.balance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.balance.balance.domain.EventType;
import br.com.balance.balance.exceptions.EventNotFoundException;


class EventTypeTest {

	@Test
	void testEventTypeGet() {
		
		for(EventType eventType: EventType.values()) {
			assertEquals(eventType, EventType.get(eventType.getCode()));
			assertEquals(eventType, EventType.get(eventType.getCode().toLowerCase()));
			assertEquals(eventType, EventType.get(eventType.getCode().toUpperCase()));
		}
		
		assertThrows(EventNotFoundException.class, () -> EventType.get("EVENTO nAO EXISTE" + UUID.randomUUID().toString()));
		
	}	


}
