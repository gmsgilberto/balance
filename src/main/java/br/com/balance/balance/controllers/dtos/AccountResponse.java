package br.com.balance.balance.controllers.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.balance.balance.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author giba
 */
@AllArgsConstructor
@Getter
public class AccountResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private BigDecimal balance;
	
	public AccountResponse(Account account){
		this(account.getId(), account.getBalance());
	}
	
}
