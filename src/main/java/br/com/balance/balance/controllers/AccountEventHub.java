package br.com.balance.balance.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.balance.balance.controllers.dtos.AccountEventRequest;
import br.com.balance.balance.controllers.dtos.AccountEventResponse;
import br.com.balance.balance.domain.AccountEventType;
import br.com.balance.balance.exceptions.AccountNotfoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class AccountEventHub {
	
	private DeposityController deposityController;
	private WithdrawController withdrawController;
	private TransferController transferController;
	
	
	@PostMapping("/event")
	public ResponseEntity<String> event(@RequestBody AccountEventRequest request){
		
		try {

			var response = execute(request);
			return ResponseEntity.status(response.getStatusCodeValue()).body(response.getBody().toJson());
			
		}catch (AccountNotfoundException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(404).body("0");
		}
		
	}


	private ResponseEntity<? extends AccountEventResponse> execute(AccountEventRequest request) {
		
		ResponseEntity<? extends AccountEventResponse> response = null;
		
		switch (AccountEventType.get(request.getType())) {
			case DEPOSITY:
				response = deposityController.deposity(request.getDestination(), request.getAmount());
				break;
			case WITHDRAW:
				response = withdrawController.withdraw(request.getOrigin(), request.getAmount());
				break;
			case TRANSFER:
				response = transferController.transfer(request.getOrigin(), request.getDestination(), request.getAmount());
				break;
		}
		return response;
	}

}
