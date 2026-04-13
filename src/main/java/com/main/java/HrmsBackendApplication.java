package com.main.java;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HrmsBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HrmsBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void run(String... args) throws Exception {
//		accountRepo.save(new Account(null, "Christopher"));
//		System.out.println(accountRepo.findAll());
//		
//	}
//	
	

}
