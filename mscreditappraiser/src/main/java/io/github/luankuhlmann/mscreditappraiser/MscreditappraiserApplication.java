package io.github.luankuhlmann.mscreditappraiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MscreditappraiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscreditappraiserApplication.class, args);
	}

}
