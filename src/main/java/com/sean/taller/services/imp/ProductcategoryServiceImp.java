package com.sean.taller.services.imp;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.repository.ProductcategoryRepository;
import com.sean.taller.services.intfcs.ProductcategoryService;

@Service
public class ProductcategoryServiceImp implements ProductcategoryService{
	
	private ProductcategoryRepository pcr;

	@Autowired
	public ProductcategoryServiceImp(ProductcategoryRepository pcr) {
		this.pcr = pcr;
	}
	
	@Override
	@Transactional
	public Productcategory save(Productcategory pc) {
		if(pc.equals(null))
			throw new IllegalArgumentException("Product category not been instanciated");
		
		if((pc.getName().replaceAll(" ", "").length() < 3))
			throw new IllegalArgumentException("Not enough characters for product category name");

		pcr.save(pc);
		
		return pcr.findById(pc.getProductcategoryid()).get();
	}

	@Override
	@Transactional
	public Productcategory edit(Productcategory pc) {
		if(pc.equals(null))
			throw new IllegalArgumentException("Product category is not instantiated");
		
		if(!(pc.getName().replaceAll(" ", "").length() < 3))
			throw new IllegalArgumentException("Not enough characters for product category");
		
		Optional<Productcategory> pcInr = pcr.findById(pc.getProductcategoryid());
		Productcategory real = null;
		
		if (pcInr.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			real = pcInr.get();
		}
		real.setModifieddate(pc.getModifieddate());
		real.setName(pc.getName());
		real.setRowguid(pc.getRowguid());
		real.setProductsubcategories(pc.getProductsubcategories());
		
		return real;
	}

	@Override
	public Iterable<Productcategory> findAll() {
		return pcr.findAll();
	}
	
	
}
