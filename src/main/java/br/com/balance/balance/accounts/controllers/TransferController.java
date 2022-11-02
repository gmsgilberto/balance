package br.com.balance.balance.accounts.controllers;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.balance.balance.accounts.controllers.response.TransferResponse;
import br.com.balance.balance.accounts.services.TransferService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TransferController {
	
	private TransferService transferService;
	
	
	@PostMapping("/event/transfer/{from}/{to}/{amount}")
	public ResponseEntity<TransferResponse> transfer( @PathVariable Integer from, 
			                                 @PathVariable Integer to, 
			                                 @PathVariable BigDecimal amount) {
		var transfer = this.transferService.transfer(from,to, amount);
		return ResponseEntity.status(201).body(new TransferResponse(transfer));
	}
	
	
}
