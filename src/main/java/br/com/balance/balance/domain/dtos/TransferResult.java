/**
 * 
 */
package br.com.balance.balance.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.balance.balance.domain.Account;
import lombok.Getter;

/**
 * @author giba
 */
@Getter
public class TransferResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final LocalDate date;
	private Account from;
	private Account to;
	
	public TransferResult(Account from, Account to) {
		super();
		this.date  = LocalDate.now();
		this.from = from;
		this.to = to;
	}
	

}
