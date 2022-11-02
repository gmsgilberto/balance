package br.com.balance.balance.accounts.controllers.response;

import br.com.balance.balance.accounts.Account;
import lombok.Getter;

/**
 * @author giba
 */
@Getter
public class DeposityResponse extends AccountEventResponse{

	private static final long serialVersionUID = 1L;

	private AccountResponse destination;
	public DeposityResponse(Account account) {
		this.destination = new AccountResponse(account);
	}
	public String toJson() {
		return String.format("{\"destination\": {\"id\":\"%s\", \"balance\":%s}}", getDestination().getId(),getDestination().getBalance());
	}
}
