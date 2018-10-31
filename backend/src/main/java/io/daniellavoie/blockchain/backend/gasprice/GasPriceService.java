package io.daniellavoie.blockchain.backend.gasprice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.daniellavoie.blockchain.backend.gasprice.entity.GasPrice;

public interface GasPriceService {
	Page<GasPrice> findAll(Pageable pageable);
}
