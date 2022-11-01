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
import br.com.balance.balance.infra.repository.AccountRepository;
import br.com.balance.balance.services.AccountService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AccountEventController {
	
	private AccountService accountService;
	private AccountRepository repository;
	
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
		var account = this.accountService.deposit(request.getDestination(), request.getAmount());
		return ResponseEntity.status(201).body(new DeposityEventResponse(account).toJson());
	}
	
	private ResponseEntity<String> withdraw(AccountEventRequest request) {
		var account = this.accountService.withdraw(request.getOrigin(), request.getAmount());
		if(account == null) {
			return ResponseEntity.status(404).body("0");
		}
		return ResponseEntity.status(201).body(new WithdrawEventResponse(account).toJson());
	}
	
	
	private ResponseEntity<String> transfer(AccountEventRequest request) {
		var from = this.accountService.transfer(request.getOrigin(), request.getDestination(), request.getAmount());
		if(from == null) {
			return ResponseEntity.status(404).body("0");
		}
		var to = this.repository.findByAccountId(request.getDestination());
		return ResponseEntity.status(201).body(new TransferEventResponse(from,to).toJson());
	}
	
	
}
