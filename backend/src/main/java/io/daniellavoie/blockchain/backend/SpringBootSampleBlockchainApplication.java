package io.daniellavoie.blockchain.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBootSampleBlockchainApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSampleBlockchainApplication.class, args);
	}
}
