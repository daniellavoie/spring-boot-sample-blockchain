package io.daniellavoie.blockchain.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@EnableScheduling
@Controller
@SpringBootApplication
public class SpringBootSampleBlockchainApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSampleBlockchainApplication.class, args);
	}

	@GetMapping({ "/", "/transaction" })
	public String redirect() {
		return "forward:/index.html";
	}
}
