package br.com.balance.balance.domain;

import br.com.balance.balance.exceptions.EventNotFoundException;
import lombok.Getter;
import lombok.NonNull;

@Getter
public enum AccountEventType {

	DEPOSITY("deposit"),
	WITHDRAW("withdraw"),
	TRANSFER("transfer");
	
	private final String code;
	
	AccountEventType(@NonNull String code){
		this.code = code;
	}
	
	public static final AccountEventType get(String code) {
		for(AccountEventType eventType: values()) {
			if(eventType.code.equalsIgnoreCase(code)) {
				return eventType;
			}
		}
		throw new EventNotFoundException(code);
	}
}
