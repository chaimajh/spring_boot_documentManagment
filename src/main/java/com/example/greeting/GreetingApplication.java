package com.example.greeting;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.greeting.business.services.UserService;


@SpringBootApplication
public class GreetingApplication {
	@Autowired
	UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(GreetingApplication.class, args);
	}

/*	@PostConstruct
    public void init() {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        userService.saveUser(new User(null, "user", "user", "user@gmail.com", roles));
    } */

}
