package io.daniellavoie.blockchain.backend.transaction;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EthereumTransactionService {
	Page<EthereumTransaction> findAll(Pageable pageable);

	List<EthereumTransaction> saveAll(List<EthereumTransaction> transactions);
}