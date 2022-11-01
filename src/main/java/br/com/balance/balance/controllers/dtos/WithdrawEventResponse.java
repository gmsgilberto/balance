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
public class WithdrawEventResponse extends AccountEventResponse{

	private static final long serialVersionUID = 1L;

	private AccountResponse origin;
	public WithdrawEventResponse(Account account) {
		this.origin = new AccountResponse(account);
	}
	
	@Override
	public String toJson() {
		return String.format("{\"origin\": {\"id\":\"%s\", \"balance\":%s}}", origin.getId(), origin.getBalance());
	}
}
