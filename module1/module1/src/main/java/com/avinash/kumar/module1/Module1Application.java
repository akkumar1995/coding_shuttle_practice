package com.avinash.kumar.module1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Module1Application implements CommandLineRunner {

	private final PaymentService paymentService1;

	private final PaymentService paymentService2;

	private final NotificationService ns;
	@Autowired
	Map<String,NotificationService> allNotificationService = new HashMap<>();

	public Module1Application(PaymentService paymentService1, PaymentService paymentService2,
//							  @Qualifier("smsNotify") NotificationService ns
		NotificationService ns) {
		this.paymentService1 = paymentService1;
		this.paymentService2 = paymentService2;
		this.ns = ns;
	}

	public static void main(String[] args) {
		SpringApplication.run(Module1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		paymentService1.pay();
//		paymentService2.pay();

		ns.send("test");
	}
}
