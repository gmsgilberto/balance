package br.com.balance.balance.accounts.exceptions;

/**
 * @author giba
 */
public class SaldoInsuficienteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException() {
		super("Saldo insuficiente");
	}


}
