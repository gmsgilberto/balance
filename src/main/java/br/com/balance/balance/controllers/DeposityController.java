package br.com.balance.balance.controllers;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.balance.balance.controllers.dtos.DeposityEventResponse;
import br.com.balance.balance.services.DeposityService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class DeposityController {
	
	private DeposityService deposityService;
	
	@PostMapping("/event/deposity/{account_id}/{amount}")
	public ResponseEntity<DeposityEventResponse> deposity(@PathVariable(name = "account_id") Integer accountId, @PathVariable BigDecimal ammount) {
		var account = this.deposityService.deposit(accountId, ammount);
		return ResponseEntity.status(201).body(new DeposityEventResponse(account));
	}
	
}
