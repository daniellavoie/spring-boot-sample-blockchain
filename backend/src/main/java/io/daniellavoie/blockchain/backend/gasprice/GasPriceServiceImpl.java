package io.daniellavoie.blockchain.backend.gasprice;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;

import io.daniellavoie.blockchain.backend.gasprice.entity.GasPrice;

@Service
public class GasPriceServiceImpl implements GasPriceService {
	private static final Logger LOGGER = LoggerFactory.getLogger(GasPriceServiceImpl.class);

	private Web3j web3j;
	private GasPriceRepository gasPriceRepository;

	public GasPriceServiceImpl(Web3j web3j, GasPriceRepository gasPriceRepository) {
		this.web3j = web3j;
		this.gasPriceRepository = gasPriceRepository;
	}

	@Override
	public Page<GasPrice> findAll(Pageable pageable) {
		return gasPriceRepository.findAll(pageable);
	}

	@Scheduled(fixedDelay = 60 * 1000)
	public void storeGasPrice() {
		try {
			gasPriceRepository
					.save(new GasPrice(0l, LocalDateTime.now(), web3j.ethGasPrice().send().getGasPrice().longValue()));
		} catch (Exception ex) {
			LOGGER.error("Failed to store new gas price.", ex);
		}
	}
}
