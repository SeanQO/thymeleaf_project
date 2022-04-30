package com.sean.taller.services.imp;

import java.math.BigDecimal;


import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sean.taller.model.prod.Product;
import com.sean.taller.repository.ProductRepository;
import com.sean.taller.repository.ProductcategoryRepository;
import com.sean.taller.repository.ProductsubcategoryRepository;
import com.sean.taller.services.intfcs.ProductService;

@Service
@Transactional
public class ProductServiceImp implements ProductService{
	
	private ProductRepository pr;
	private ProductsubcategoryRepository sr;
	private ProductcategoryRepository cr;
	
	public ProductServiceImp(ProductRepository pr, ProductsubcategoryRepository sr, ProductcategoryRepository cr) {
		this.pr = pr;
		this.sr = sr;
		this.cr = cr;
	}
	
	@Override
	public Product save(Product p) {
		if(p == null) 
			throw new NullPointerException("product does not exist");
		
		if( p.getWeight() != null)
			throw new NullPointerException();
		
		if (p.getProductsubcategory() != null)
			throw new NullPointerException();
		
		if (p.getSize() != null) 
			throw new NullPointerException();
		
		if (p.getProductsubcategory().getProductcategory() != null)
			throw new NullPointerException();
		
		if (sr.existsById(p.getProductsubcategory().getProductsubcategoryid()) &&
				cr.existsById(p.getProductsubcategory().getProductcategory().getProductcategoryid()))
		
		
		if(p.getProductnumber().equals(null))
			throw new IllegalArgumentException("Product number does not exist");
		
		if(p.getSellstartdate().compareTo(p.getSellenddate()) >= 0)
			throw new IllegalArgumentException("Product sell end date is equal or lesser than the sell start date	");

		/*
		 *
		if(p.getSize() > 0)
			throw new IllegalArgumentException("Invalid product size");
		*/
		
		if(p.getWeight().compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("Invalid product weigth");
		
		pr.save(p);
		
		return pr.findById(p.getProductid()).get();
	}

	@Override
	public Product edit(Product p) {
		if(p == null) 
			throw new NullPointerException("product does not exist");
		
		if( p.getWeight() != null)
			throw new NullPointerException();
		
		if (p.getProductsubcategory() != null)
			throw new NullPointerException();
		
		if (p.getSize() != null) 
			throw new NullPointerException();
		
		if (p.getProductsubcategory().getProductcategory() != null)
			throw new NullPointerException();
		
		if (sr.existsById(p.getProductsubcategory().getProductsubcategoryid()) &&
				cr.existsById(p.getProductsubcategory().getProductcategory().getProductcategoryid()))
		
		if(p.getProductnumber().equals(null))
			throw new IllegalArgumentException("Product number does not exist");
		
		if(p.getSellstartdate().compareTo(p.getSellenddate()) >= 0)
			throw new IllegalArgumentException("Product sell end date is equal or lesser than the sell start date	");
		
		if(p.getWeight().compareTo(BigDecimal.ZERO) <= 0)
			throw new IllegalArgumentException("Invalid product weigth");
		
		Optional<Product> pInr = pr.findById(p.getProductid());
		Product real = null;
		
		if (pInr.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			real = pInr.get();
		}
		
		real.setBillofmaterials1(p.getBillofmaterials1());
		real.setBillofmaterials2(p.getBillofmaterials2());
		real.setClass_(p.getClass_());
		real.setColor(p.getColor());
		real.setDaystomanufacture(p.getDaystomanufacture());
		real.setDiscontinueddate(p.getDiscontinueddate());
		real.setFinishedgoodsflag(p.getFinishedgoodsflag());
		real.setListprice(p.getListprice());
		real.setMakeflag(p.getMakeflag());
		real.setModifieddate(p.getModifieddate());
		real.setName(p.getName());
		real.setProductcosthistories(p.getProductcosthistories());
		real.setProductdocuments(p.getProductdocuments());
		real.setProductinventories(p.getProductinventories());
		real.setProductline(p.getProductline());
		real.setProductlistpricehistories(p.getProductlistpricehistories());
		real.setProductmodel(p.getProductmodel());
		real.setProductnumber(p.getProductnumber());
		real.setProductproductphotos(p.getProductproductphotos());
		real.setProductreviews(p.getProductreviews());
		real.setProductsubcategory(p.getProductsubcategory());
		real.setReorderpoint(p.getReorderpoint());
		real.setRowguid(p.getRowguid());
		real.setSafetystocklevel(p.getSafetystocklevel());
		real.setSellenddate(p.getSellenddate());
		real.setSellstartdate(p.getSellstartdate());
		real.setSize(p.getSize());
		real.setStandardcost(p.getStandardcost());
		real.setStyle(p.getStyle());
		real.setTransactionhistories(p.getTransactionhistories());
		real.setUnitmeasure1(p.getUnitmeasure1());
		real.setUnitmeasure2(p.getUnitmeasure2());
		real.setWeight(p.getWeight());
		real.setWorkorders(p.getWorkorders());
		
		return real;
	}
	
	@Override
	public Iterable<Product> findAll() {
		return pr.findAll();
	}

}
