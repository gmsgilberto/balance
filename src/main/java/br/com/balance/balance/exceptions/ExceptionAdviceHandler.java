package br.com.balance.balance.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionAdviceHandler {

	@ExceptionHandler(value = { AccountNotfoundException.class})
	public ResponseEntity<String> accountNotFoundExceptionHandler(AccountNotfoundException ex, WebRequest request) {
		return ResponseEntity.status(404).body(ex.getMessage());
	}

}
