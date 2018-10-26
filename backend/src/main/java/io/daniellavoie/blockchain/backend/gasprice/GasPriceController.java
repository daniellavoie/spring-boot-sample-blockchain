package io.daniellavoie.blockchain.backend.gasprice;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.daniellavoie.blockchain.backend.gasprice.entity.GasPrice;

@RestController
@RequestMapping("/api/gas-price")
public class GasPriceController {
	private GasPriceService gasPriceService;

	public GasPriceController(GasPriceService gasPriceService) {
		this.gasPriceService = gasPriceService;
	}

	@GetMapping
	@ResponseBody
	public Page<GasPrice> getGasPrices(Pageable pageable) throws IOException {
		return gasPriceService.findAll(pageable);
	}
}
