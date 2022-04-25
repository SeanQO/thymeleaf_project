package com.sean.taller.services.imp;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sean.taller.model.prod.Productcategory;
import com.sean.taller.model.prod.Productsubcategory;
import com.sean.taller.repository.ProductcategoryRepository;
import com.sean.taller.repository.ProductsubcategoryRepository;
import com.sean.taller.services.intfcs.ProductsubcategoryService;

@Service
@Transactional
public class ProductsubcategoryServiceImp implements ProductsubcategoryService{

	private ProductsubcategoryRepository pscr;
	private ProductcategoryRepository pcr;
	
	public ProductsubcategoryServiceImp(ProductsubcategoryRepository pscr, ProductcategoryRepository pcr) {
		this.pscr = pscr;
		this.pcr = pcr;
	}
	
	@Override
	public Productsubcategory save(Productsubcategory psc) {
		if(psc.equals(null))
			throw new IllegalArgumentException("Product sub-category is not instantiated");
		
		if(!(psc.getName().replaceAll(" ", "").length() < 5))
			throw new IllegalArgumentException("Not enough characters for product category");
		
		Optional<Productcategory> tempPc = pcr.findById(psc.getProductcategory().getProductcategoryid());
		
		if(tempPc == null)
			throw new IllegalArgumentException("Product category does not exist");
		
		pscr.save(psc);
		
		return pscr.findById(psc.getProductsubcategoryid()).get();
	}

	@Override
	public Productsubcategory edit(Productsubcategory psc) {
		if(psc.equals(null))
			throw new IllegalArgumentException("Product sub-category is not instantiated");
		
		if(!(psc.getName().replaceAll(" ", "").length() < 5))
			throw new IllegalArgumentException("Not enough characters for product category");
		
		Optional<Productcategory> tempPc = pcr.findById(psc.getProductcategory().getProductcategoryid());
		
		if(tempPc == null)
			throw new IllegalArgumentException("Product category does not exist");
		
		Optional<Productsubcategory> pscInr = pscr.findById(psc.getProductsubcategoryid());
		Productsubcategory real = null;
		
		if (pscInr.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			real = pscInr.get();
		}
		real.setModifieddate(psc.getModifieddate());
		real.setName(psc.getName());
		real.setProductcategory(psc.getProductcategory());
		real.setProducts(psc.getProducts());
		real.setRowguid(psc.getRowguid());
		
		return real;
	}

}
