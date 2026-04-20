package com.larvae.backend_template;

import com.larvae.backend_template.entity.Account;
import com.larvae.backend_template.enums.AccountStatus;
import com.larvae.backend_template.enums.Roles;
import com.larvae.backend_template.features.auth.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendTemplateApplication implements CommandLineRunner {

	private final AccountRepository accountRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BackendTemplateApplication(AccountRepository accountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public static void main(String[] args) {
		SpringApplication.run(BackendTemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if(accountRepository.findByUsernameAndIsActiveTrue("admin").isEmpty()){
			Account adminAcc = new Account();
			adminAcc.setUsername("admin");
			adminAcc.setPassword(bCryptPasswordEncoder.encode("Admin@123"));
			adminAcc.setRole(Roles.ROLE_ADMIN);
			adminAcc.setStatus(AccountStatus.ACTIVE);
			accountRepository.save(adminAcc);
			System.out.println("Default admin auth created: username=admin, password=Admin@123");
		}

	}

	

}
