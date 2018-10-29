package io.daniellavoie.blockchain.backend.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EthereumTransactionRepository extends JpaRepository<EthereumTransaction, String> {

}
