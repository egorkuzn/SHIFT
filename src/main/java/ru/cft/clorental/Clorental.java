package ru.cft.clorental;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.cft.clorental.domain.RoleEntity;
import ru.cft.clorental.domain.UserEntity;
import ru.cft.clorental.service.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class Clorental {

	public static void main(String[] args) {
		SpringApplication.run(Clorental.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
