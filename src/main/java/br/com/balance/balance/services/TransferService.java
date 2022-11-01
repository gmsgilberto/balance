package br.com.balance.balance.services;

import java.math.BigDecimal;

import br.com.balance.balance.domain.dtos.TransferResult;

/**
 * @author giba
 */
public interface TransferService {

	/**
	 * @param from
	 * @param to
	 * @param amount
	 * @return
	 */
	TransferResult transfer(Integer from, Integer to, BigDecimal amount);
	
}
