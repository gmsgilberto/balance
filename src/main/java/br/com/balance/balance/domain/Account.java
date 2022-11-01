package br.com.balance.balance.domain;

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

	public Account(Account account) {
		this(account.id, account.balance);
	}

	public Account deposity(BigDecimal amount) {
		if(amount != null) {
			this.balance = this.balance.add(amount);
		}
		return this;
	}

	public Account withdraw(BigDecimal amount) {
		if(amount != null) {
			this.balance = this.balance.subtract(amount);
		}
		return this;
	}
	
	@Override
	public String toString() {
		return String.format("AccountId: %s, Balance: %s", this.id, this.balance);
	}
	
}
