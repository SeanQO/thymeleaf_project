package com.sean.taller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.model.prod.Unitmeasure;
import com.sean.taller.services.imp.UserServiceImp;
import com.sean.taller.services.intfcs.ProductService;
import com.sean.taller.services.intfcs.ProductcategoryService;
import com.sean.taller.services.intfcs.ProductsubcategoryService;
import com.sean.taller.services.intfcs.UnitmeasureService;
import com.sean.taller.services.intfcs.UserService;
import com.sean.taller.user.UserEntity;
import com.sean.taller.user.UserType;

@SpringBootApplication
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
		
		ProductcategoryService pcs = c.getBean(ProductcategoryService.class);
		
		Productcategory pc1 = new Productcategory();
		pc1.setName("Phone");
		LocalDate date = LocalDate.now();    
		pc1.setModifieddate(date);
		pc1.setRowguid(1);
		pcs.save(pc1);
		
		ProductsubcategoryService pscs = c.getBean(ProductsubcategoryService.class);
		Productsubcategory psc1 = new Productsubcategory();
		psc1.setName("Android");
		date = LocalDate.now();    
		psc1.setModifieddate(date);
		psc1.setRowguid(1);
		psc1.setProductcategory(pc1);
		pscs.save(psc1);
		
		UnitmeasureService ums =  c.getBean(UnitmeasureService.class);
		
		Unitmeasure um1 = new Unitmeasure();
		um1.setName("kg");
		ums.save(um1);
		Unitmeasure um2 = new Unitmeasure();
		um2.setName("ml");
		ums.save(um2);

		ProductService ps =  c.getBean(ProductService.class);
		Product p1 = new Product();
		p1.setName("samsung");
		p1.setProductnumber("1");
		p1.setSellstartdate(LocalDate.of(2022, 05, 8));
		p1.setSellenddate(LocalDate.of(2023, 01, 8));
		p1.setProductsubcategory(psc1);
		p1.setUnitmeasure1(um1);
		p1.setUnitmeasure2(um2);
		p1.setSize("S");
		p1.setWeight(BigDecimal.valueOf(80));
		ps.save(p1);
	}
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
}


