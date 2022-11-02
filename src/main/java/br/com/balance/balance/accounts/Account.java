package br.com.balance.balance.accounts;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author giba
 */
@Getter
@AllArgsConstructor
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private BigDecimal balance;

	public Account(Account account, BigDecimal newBalance) {
		this(account);
		this.balance = newBalance;
	}

	public Account(Account account) {
		this(account.id, account.balance);
	}

	public Account deposity(BigDecimal amount) {
		return DeposityStrategy
				.getInstance()
				.execute(this, amount);
	}

	public Account withdraw(BigDecimal amount) {
		return WithdrawStrategy
				.getInstance()
				.execute(this, amount);
	}
	
	@Override
	public String toString() {
		return String.format("AccountId: %s, Balance: %s", this.id, this.balance);
	}
	
}
