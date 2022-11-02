package br.com.balance.balance.accounts.controllers.response;

import br.com.balance.balance.accounts.Account;
import lombok.Getter;

/**
 * @author giba
 */
@Getter
public class WithdrawResponse extends AccountEventResponse{

	private static final long serialVersionUID = 1L;

	private AccountResponse origin;
	public WithdrawResponse(Account account) {
		this.origin = new AccountResponse(account);
	}
	
	@Override
	public String toJson() {
		return String.format("{\"origin\": {\"id\":\"%s\", \"balance\":%s}}", origin.getId(), origin.getBalance());
	}
}
