package br.com.balance.balance.controllers.dtos;

import br.com.balance.balance.domain.dtos.TransferResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author giba
 */
@Getter
@Setter
@NoArgsConstructor
public class TransferEventResponse extends AccountEventResponse{

	private static final long serialVersionUID = 1L;

	private AccountResponse destination;
	private AccountResponse origin;
	public TransferEventResponse(TransferResult transferResult) {
		this.origin = new AccountResponse(transferResult.getFrom());
		this.destination = new AccountResponse(transferResult.getTo());
	}
	
	@Override
	public String toJson() {
		return String.format("{\"origin\": {\"id\":\"%s\", \"balance\":%s}, \"destination\": {\"id\":\"%s\", \"balance\":%s}}",
					origin.getId(),
					origin.getBalance(),
					destination.getId(),
					destination.getBalance()
				);
	}
}
