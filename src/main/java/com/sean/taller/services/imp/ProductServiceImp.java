package com.sean.taller.services.imp;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sean.taller.model.prod.Product;
import com.sean.taller.repository.ProductRepository;
import com.sean.taller.services.intfcs.ProductService;

@Service
@Transactional
public class ProductServiceImp implements ProductService{
	
	private ProductRepository pr;
	
	
	public ProductServiceImp(ProductRepository pr) {
		this.pr = pr;
	}
	
	@Override
	public Product save(Product p) {
		if(p == null) 
			throw new NullPointerException("product does not exist");
		
		if( p.getWeight() == null)
			throw new NullPointerException();
		
//		if (p.getProductsubcategory() == null)
//			throw new NullPointerException();
//		
		if (p.getSize() == null) 
			throw new NullPointerException();
		
//		if (p.getProductsubcategory().getProductcategory() == null)
//			throw new NullPointerException();
//		
//		if (sr.existsById(p.getProductsubcategory().getProductsubcategoryid()) &&
//				cr.existsById(p.getProductsubcategory().getProductcategory().getProductcategoryid()))
//		
		
		if(p.getProductnumber() == null)
			throw new IllegalArgumentException("Product number does not exist");
		
		if(p.getSellstartdate().compareTo(p.getSellenddate()) >= 0)
			throw new IllegalArgumentException("Product sell end date is equal or lesser than the sell start date	");
		
		if(p.getWeight().compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("Invalid product weigth");
		
		pr.save(p);
		
		return pr.findById(p.getProductid()).get();
	}

	@Override
	public Product edit(Product p) {
		
		if(p == null) 
			throw new NullPointerException("product does not exist");
		
		Optional<Product> deletedP = pr.findById(p.getProductid());
		Product dp = null;
		
		if (deletedP.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			dp = deletedP.get();
		}
		
		if( p.getWeight() == null)
			throw new NullPointerException();
		
//		if (p.getProductsubcategory() == null)
//			throw new NullPointerException();
		
		if (p.getSize() == null) 
			throw new NullPointerException();
		
//		if (p.getProductsubcategory().getProductcategory() == null)
//			throw new NullPointerException();
//		
//		if (sr.existsById(p.getProductsubcategory().getProductsubcategoryid()) &&
//				cr.existsById(p.getProductsubcategory().getProductcategory().getProductcategoryid()))
//		
		if(p.getProductnumber() == null)
			throw new IllegalArgumentException("Product number does not exist");
		
		if(p.getSellstartdate().compareTo(p.getSellenddate()) >= 0)
			throw new IllegalArgumentException("Product sell end date is equal or lesser than the sell start date	");
		
		if(p.getWeight().compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("Invalid product weigth");
		
		pr.deleteById(dp.getProductid());
		pr.save(p);
		return p;
	}
	
	@Override
	public Iterable<Product> findAll() {
		return pr.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return pr.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		pr.deleteById(id);
	}

}
