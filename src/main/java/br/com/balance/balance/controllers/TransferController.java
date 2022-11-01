package br.com.balance.balance.controllers;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.balance.balance.controllers.dtos.TransferEventResponse;
import br.com.balance.balance.services.TransferService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TransferController {
	
	private TransferService transferService;
	
	
	@PostMapping("/event/transfer/{from}/{to}/{amount}")
	public ResponseEntity<TransferEventResponse> transfer( @PathVariable Integer from, 
			                                 @PathVariable Integer to, 
			                                 @PathVariable BigDecimal amount) {
		var transfer = this.transferService.transfer(from,to, amount);
		return ResponseEntity.status(201).body(new TransferEventResponse(transfer));
	}
	
	
}
