package com.bolsadeideas.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootDirecionFomentoEducativoApplication implements CommandLineRunner {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDirecionFomentoEducativoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String myPassword = "12345";
		for (int i = 0; i < 2; i++) {
			String byEncoder = passwordEncoder.encode(myPassword);
			System.out.println(byEncoder);
		}
	}

}
