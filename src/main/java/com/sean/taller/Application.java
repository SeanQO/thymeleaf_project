package com.sean.taller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sean.taller.dao.imp.ProductDaoImp;
import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.model.prod.Unitmeasure;
import com.sean.taller.repository.ProductcategoryRepository;
import com.sean.taller.repository.ProductsubcategoryRepository;
import com.sean.taller.repository.UnitmeasureRepository;
import com.sean.taller.services.imp.UserServiceImp;
import com.sean.taller.services.intfcs.UserService;
import com.sean.taller.user.UserEntity;
import com.sean.taller.user.UserType;

@SpringBootApplication
@EnableJpaRepositories("com.sean.taller.repository")
@ComponentScan(basePackages = {"com.sean.taller"})	
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
	
	@Bean
	public CommandLineRunner add(ProductcategoryRepository pcr, ProductsubcategoryRepository pscr, UnitmeasureRepository umr, ProductDaoImp pr) {
		return (args) -> {
			Productcategory pc1 = new Productcategory();
			pc1.setName("Comida");
			pcr.save(pc1);
			
			Productsubcategory psc1 = new Productsubcategory();
			psc1.setName("Fruta");
			psc1.setProductcategory(pc1);
			pscr.save(psc1);
			
			Unitmeasure um1 = new Unitmeasure();
			um1.setName("kg");
			umr.save(um1);
			Unitmeasure um2 = new Unitmeasure();
			um2.setName("pounds");
			umr.save(um2);


			Product p1 = new Product();
			p1.setName("Piña");
			p1.setDaystomanufacture(360);
			p1.setProductnumber("123");
			p1.setProductsubcategory(psc1);
			p1.setUnitmeasure1(um1);
			p1.setUnitmeasure2(um2);
			pr.save(p1);
	
		};
	}

}
