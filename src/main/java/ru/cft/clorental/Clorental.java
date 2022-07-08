package ru.cft.clorental;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.cft.clorental.domain.Role;
import ru.cft.clorental.domain.User;
import ru.cft.clorental.service.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class Clorental {

	public static void main(String[] args) {
		SpringApplication.run(Clorental.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "John Travota", "john", "1234", new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_SUPER_ADMIN");
		};
	}
}
