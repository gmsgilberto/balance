package br.com.balance.balance.controllers.dtos;

import br.com.balance.balance.domain.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author giba
 */
@Getter
@Setter
@NoArgsConstructor
public class DeposityEventResponse extends AccountEventResponse{

	private static final long serialVersionUID = 1L;

	private AccountResponse destination;
	public DeposityEventResponse(Account account) {
		this.destination = new AccountResponse(account);
	}
	public String toJson() {
		return String.format("{\"destination\": {\"id\":\"%s\", \"balance\":%s}}", destination.getId(),destination.getBalance());
	}
}
