package io.daniellavoie.blockchain.tests.backend;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GasPriceTests {
	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void canRetreiveGasPrice() {
		Assert.assertNotNull(Integer.valueOf(restTemplate.getForEntity("/gas-price", String.class).getBody()));
	}
}