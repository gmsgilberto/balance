package br.com.balance.balance.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.balance.balance.infra.repository.AccountRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AdminController {
	
	private AccountRepository accountRepository;
	
	@PostMapping(path = "reset")
	public ResponseEntity<String> reset(){
		this.accountRepository.reset();
		return ResponseEntity.status(200).body("OK");
	}
	
}
