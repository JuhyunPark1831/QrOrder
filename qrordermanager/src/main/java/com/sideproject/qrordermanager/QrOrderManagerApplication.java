package com.sideproject.qrordermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class QrOrderManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrOrderManagerApplication.class, args);
	}

}
