package com.sean.taller.test.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sean.taller.Application;
import com.sean.taller.dao.imp.ProductDaoImp;
import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.model.prod.Unitmeasure;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = Application.class)
class ProductDaoTest {
	@Autowired
	private ProductDaoImp pd;
	
	@BeforeAll
	public void setup() {
		Productcategory pc1 = new Productcategory();
		pc1.setName("Phones");
		
		Productsubcategory psc1 = new Productsubcategory();
		psc1.setName("IOS");
		psc1.setProductcategory(pc1);
		
		Unitmeasure um1 = new Unitmeasure();
		um1.setName("kg2");
		Unitmeasure um2 = new Unitmeasure();
		um2.setName("ml2");
		
		Product p1 = new Product();
		p1.setName("iphone");
		p1.setDaystomanufacture(1250);
		p1.setProductnumber("123");
		p1.setProductsubcategory(psc1);
		p1.setUnitmeasure1(um1);
		p1.setUnitmeasure2(um2);
		pd.save(p1);
	}
	
	
	@Test
	public void save() {
		Productcategory pc1 = new Productcategory();
		pc1.setName("Phones");
		
		Productsubcategory psc1 = new Productsubcategory();
		psc1.setName("IOS");
		psc1.setProductcategory(pc1);
		
		Unitmeasure um1 = new Unitmeasure();
		um1.setName("kg2");
		Unitmeasure um2 = new Unitmeasure();
		um2.setName("ml2");
		
		Product p1 = new Product();
		p1.setName("iphone");
		p1.setDaystomanufacture(1250);
		p1.setProductnumber("123");
		p1.setProductsubcategory(psc1);
		p1.setUnitmeasure1(um1);
		p1.setUnitmeasure2(um2);
		
		pd.save(p1);
		Product pfromDao = pd.findById(p1.getProductid());
		assertEquals(pfromDao.getProductid(), p1.getProductid());
	}
	
	@Test
	public void edit() {
		Product p = pd.findById(1);
		p.setName("cambiado");
		pd.update(p);
		assertTrue(pd.findById(1).getName().equals("cambiado"));
	}
	
	@Test
	public void find() {
		Product p = pd.findById(1);
		assertTrue(p.getName().equals("iphone"));
	}
	
	@Test
	public void findAll() {
		assertTrue(pd.findAll().size() >= 1);
	}

}
