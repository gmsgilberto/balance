package br.com.balance.balance.controllers;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.balance.balance.controllers.dtos.WithdrawEventResponse;
import br.com.balance.balance.services.WithdrawService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WithdrawController {
	
	private WithdrawService withdrawService;
	
	@PostMapping("/event/withdraw/{account_id}/{amount}")
	public ResponseEntity<WithdrawEventResponse> withdraw( @PathVariable(name = "account_id") Integer accountId, 
			                                              @PathVariable BigDecimal amount) {
		var account = this.withdrawService.withdraw(accountId,amount);
		return ResponseEntity.status(201).body(new WithdrawEventResponse(account));
	}
	
}
