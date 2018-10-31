package io.daniellavoie.blockchain.backend.gasprice;

import org.springframework.data.jpa.repository.JpaRepository;

import io.daniellavoie.blockchain.backend.gasprice.entity.GasPrice;

public interface GasPriceRepository extends JpaRepository<GasPrice, Long> {

}
