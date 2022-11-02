package br.com.balance.balance.accounts.controllers.response;

import java.io.Serializable;

/**
 * @author giba
 */
public abstract class AccountEventResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	public abstract String toJson() ;
	
}
