package br.com.balance.balance.domain;

import br.com.balance.balance.exceptions.EventNotFoundException;
import lombok.Getter;
import lombok.NonNull;

@Getter
public enum EventType {

	DEPOSITY("deposit"),
	WITHDRAW("withdraw"),
	TRANSFER("transfer");
	
	private final String code;
	
	EventType(@NonNull String code){
		this.code = code;
	}
	
	public static final EventType get(String code) {
		for(EventType eventType: values()) {
			if(eventType.code.equalsIgnoreCase(code)) {
				return eventType;
			}
		}
		throw new EventNotFoundException(code);
	}
}
