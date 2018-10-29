package io.daniellavoie.blockchain.backend.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class EthereumTransactionController {
	private EthereumTransactionService ethereumTransactionService;

	public EthereumTransactionController(EthereumTransactionService ethereumTransactionService) {
		this.ethereumTransactionService = ethereumTransactionService;
	}

	@GetMapping
	public Page<EthereumTransaction> findAll(Pageable pageable) {
		return ethereumTransactionService.findAll(pageable);
	}
}
