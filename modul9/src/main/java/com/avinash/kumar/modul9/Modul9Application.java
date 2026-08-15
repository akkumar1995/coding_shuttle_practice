package com.avinash.kumar.modul9;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class Modul9Application {
	@PostConstruct
	public void init() {
		// Setting the JVM timezone explicitly to match PostgreSQL expectations
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
	}

	public static void main(String[] args) {
		SpringApplication.run(Modul9Application.class, args);
	}

}
