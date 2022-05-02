package com.sean.taller.services.intfcs;

import com.sean.taller.model.prod.Productcategory;

public interface ProductcategoryService {
	public Productcategory save(Productcategory pc);
	public Productcategory edit(Productcategory pc);
	Iterable<Productcategory> findAll();
}
