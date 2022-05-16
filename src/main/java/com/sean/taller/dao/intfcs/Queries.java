package com.sean.taller.dao.intfcs;

import java.util.List;

import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productsubcategory;

public interface Queries {
	public List<Productsubcategory> findProdCategByProdSubCateg();
	public List<Product> findProductByWorkOrder();
}
