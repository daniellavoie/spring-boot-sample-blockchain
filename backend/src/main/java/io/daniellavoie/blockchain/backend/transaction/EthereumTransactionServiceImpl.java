package io.daniellavoie.blockchain.backend.transaction;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;

@Service
public class EthereumTransactionServiceImpl implements EthereumTransactionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EthereumTransactionServiceImpl.class);

	private EthereumTransactionRepository ethereumTransactionRepository;
	private Web3j web3j;

	public EthereumTransactionServiceImpl(EthereumTransactionRepository ethereumTransactionRepository, Web3j web3j) {
		this.ethereumTransactionRepository = ethereumTransactionRepository;
		this.web3j = web3j;
	}

	@Override
	public Page<EthereumTransaction> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return ethereumTransactionRepository.findAll(pageable);
	}

	@Override
	public List<EthereumTransaction> saveAll(List<EthereumTransaction> transactions) {
		// TODO Auto-generated method stub
		return ethereumTransactionRepository.saveAll(transactions);
	}

	@Scheduled(fixedDelay = 60 * 1000)
	public void storeTransactions() {
		try {
			long loadTransactionsFrom;
			if (ethereumTransactionRepository.count() == 0) {
				loadTransactionsFrom = web3j.ethBlockNumber().send().getBlockNumber().longValue() - 10;
			} else {
				loadTransactionsFrom = ethereumTransactionRepository
						.findAll(PageRequest.of(0, 1, Direction.DESC, "blockNumber")).getContent().get(0)
						.getBlockNumber();
			}

			LOGGER.info("Processing ethereum transactions from block number " + loadTransactionsFrom + ".");

			web3j.catchUpToLatestTransactionObservable(
					DefaultBlockParameter.valueOf(BigInteger.valueOf(loadTransactionsFrom)))

					.map(transaction -> new EthereumTransaction(transaction.getBlockHash(), transaction.getFrom(),
							transaction.getTo(), transaction.getBlockNumber().longValue(),
							transaction.getValue().longValue()))

					.buffer(100)

					.doOnNext(transactions -> LOGGER
							.info("Found " + transactions.size() + " ethereum transactions to persist."))

					.filter(transactions -> transactions.size() > 0)

					.map(ethereumTransactionRepository::saveAll)

					.doOnNext(
							transactions -> LOGGER.info("Persisted " + transactions.size() + " ethereum transactions."))

					.toBlocking().subscribe();
		} catch (Exception ex) {
			LOGGER.error("Error while processing ethereum transactions", ex);
		}
	}
}
