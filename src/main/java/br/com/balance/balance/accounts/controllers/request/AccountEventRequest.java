package br.com.balance.balance.accounts.controllers.request;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author giba
 */
@Getter
@Setter
public class AccountEventRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private String type;
	private Integer destination;
	private Integer origin;
	private BigDecimal amount;
	
}
