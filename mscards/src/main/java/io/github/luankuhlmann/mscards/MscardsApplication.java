package io.github.luankuhlmann.mscards;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
@Slf4j
public class MscardsApplication {
	public static void main(String[] args) {
		log.info("Information: {}", "test info");
		log.info("Error: {}", "test error");
		log.info("Warn: {}", "test warn");

		SpringApplication.run(MscardsApplication.class, args);
	}

}
