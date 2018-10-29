package io.daniellavoie.blockchain.backend.transaction;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EthereumTransaction {
	private String id;
	private String sender;
	private String receiver;
	private long blockNumber;
	private long value;

	public EthereumTransaction() {

	}

	public EthereumTransaction(String id, String sender, String receiver, long blockNumber, long value) {
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.blockNumber = blockNumber;
		this.value = value;
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public long getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(long blockNumber) {
		this.blockNumber = blockNumber;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
}
