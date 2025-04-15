package com.sideProject.qrOrder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class QrOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrOrderApplication.class, args);
	}

}
