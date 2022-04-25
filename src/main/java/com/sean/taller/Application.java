package com.sean.taller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.sean.taller.services.imp.UserServiceImp;
import com.sean.taller.services.intfcs.UserService;
import com.sean.taller.user.UserEntity;
import com.sean.taller.user.UserType;

@SpringBootApplication
@ComponentScan(basePackages = {"com.edu.taller.ortiz.isabella"})	
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(Application.class, args);
		
		UserService usi = c.getBean(UserServiceImp.class);
		
		UserEntity ue1 = new UserEntity();
		ue1.setId(123456);
		ue1.setUsername("admin");
		ue1.setPassword("{noop}admin");
		ue1.setType(UserType.ADMINISTRATOR);
		usi.save(ue1);
		
		UserEntity ue2 = new UserEntity();
		ue2.setId(456789);
		ue2.setUsername("operator");
		ue2.setPassword("{noop}operator");
		ue2.setType(UserType.OPERATOR);
		usi.save(ue2);
		
	}

}
