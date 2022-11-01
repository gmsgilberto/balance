package br.com.balance.balance.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.balance.balance.controllers.dtos.AccountEventRequest;
import br.com.balance.balance.controllers.dtos.DeposityEventResponse;
import br.com.balance.balance.controllers.dtos.TransferEventResponse;
import br.com.balance.balance.controllers.dtos.WithdrawEventResponse;
import br.com.balance.balance.domain.EventType;
import br.com.balance.balance.services.DeposityService;
import br.com.balance.balance.services.TransferService;
import br.com.balance.balance.services.WithdrawService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AccountEventController {
	
	private TransferService transferService;
	private DeposityService deposityService;
	private WithdrawService withdrawService;
	
	@PostMapping("/event")
	public ResponseEntity<String> event(@RequestBody AccountEventRequest request){
		
		var type = EventType.get(request.getType());
		switch (type) {
			case DEPOSITY:
				return deposity(request);
			case WITHDRAW:
				return withdraw(request);
			case TRANSFER:
				return transfer(request);
		}
		
		return ResponseEntity.badRequest().build();
		
	}

	private ResponseEntity<String> deposity(AccountEventRequest request) {
		var account = this.deposityService.deposit(request.getDestination(), request.getAmount());
		return ResponseEntity.status(201).body(new DeposityEventResponse(account).toJson());
	}
	
	private ResponseEntity<String> withdraw(AccountEventRequest request) {
		var account = this.withdrawService.withdraw(request.getOrigin(), request.getAmount());
		if(account == null) {
			return ResponseEntity.status(404).body("0");
		}
		return ResponseEntity.status(201).body(new WithdrawEventResponse(account).toJson());
	}
	
	
	private ResponseEntity<String> transfer(AccountEventRequest request) {
		var transfer = this.transferService.transfer(request.getOrigin(), request.getDestination(), request.getAmount());
		if(transfer == null) {
			return ResponseEntity.status(404).body("0");
		}
		
		return ResponseEntity.status(201).body(new TransferEventResponse(transfer).toJson());
	}
	
	
}
