package io.daniellavoie.blockchain.tests;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootApplication
public class SpringBootSampleBlockchainAcceptanceTestApplication {
	@Bean
	public RestTemplate restTemplate(@Value("${blockchain.backend.url}") String blockchainBackendUrl) {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(blockchainBackendUrl));

		return restTemplate;
	}
}
