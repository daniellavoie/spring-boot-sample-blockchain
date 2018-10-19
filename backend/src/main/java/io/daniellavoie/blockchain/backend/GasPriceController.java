package io.daniellavoie.blockchain.backend;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.Web3j;

@RestController
@RequestMapping("/gas-price")
public class GasPriceController {
	private Web3j web3j;

	public GasPriceController(Web3j web3j) {
		this.web3j = web3j;
	}

	@GetMapping
	@ResponseBody
	public Integer getGasPrice() throws IOException {
		return web3j.ethGasPrice().send().getGasPrice().intValue();
	}
}
