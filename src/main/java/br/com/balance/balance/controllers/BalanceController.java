package br.com.balance.balance.controllers;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.balance.balance.infra.repository.AccountRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BalanceController {
	
	private AccountRepository accountRepository;
	
	@GetMapping(path = "balance")
	public ResponseEntity<BigDecimal> getBalance( @RequestParam(name = "account_id", required = true) Integer accounId){
		
		var account = this.accountRepository.findByAccountId(accounId);
		if(account == null) {
			return ResponseEntity.status(404).body(BigDecimal.ZERO);
		}
		
		return ResponseEntity.status(200).body(account.getBalance());
	}
	
	
}
