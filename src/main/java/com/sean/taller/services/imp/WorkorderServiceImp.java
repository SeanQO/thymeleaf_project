package com.sean.taller.services.imp;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sean.taller.model.prod.Workorder;
import com.sean.taller.repository.WorkorderRepository;
import com.sean.taller.services.intfcs.WorkorderService;

@Service
@Transactional
public class WorkorderServiceImp implements WorkorderService{
	private WorkorderRepository wor;
	
	public WorkorderServiceImp(WorkorderRepository wor) {
		this.wor = wor;
	}
	
	@Override
	public Workorder save(Workorder wo) {
		
		if(wo.equals(null))
			throw new NullPointerException("Work order does not exist");
		
		if(wo.getOrderqty() >= 0)
			throw new IllegalArgumentException("Invalid order quantity");
		
		if(wo.getScrappedqty() >= 0)
			throw new IllegalArgumentException("Invalid scrapped  quantity");
		
		if(wo.getDuedate().compareTo(wo.getEnddate()) >= 0)
			throw new IllegalArgumentException("Product sell end date is equal or lesser than the sell start date");
		
		wor.save(wo);
		
		return wor.findById(wo.getWorkorderid()).get();
	}

	@Override
	public Workorder edit(Workorder wo) {
		if(wo.equals(null))
			throw new NullPointerException("Work order does not exist");
		
		if(wo.getOrderqty() >= 0)
			throw new IllegalArgumentException("Invalid order quantity");
		
		if(wo.getScrappedqty() >= 0)
			throw new IllegalArgumentException("Invalid scrapped  quantity");
		
		if(wo.getDuedate().compareTo(wo.getEnddate()) >= 0)
			throw new IllegalArgumentException("Product sell end date is equal or lesser than the sell start date");
		
		Optional<Workorder> woInr = wor.findById(wo.getWorkorderid());
		Workorder real = null;
		
		if (woInr.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			real = woInr.get();
		}
		real.setDuedate(wo.getDuedate());
		real.setEnddate(wo.getEnddate());
		real.setModifieddate(wo.getModifieddate());
		real.setOrderqty(wo.getOrderqty());
		real.setProduct(wo.getProduct());
		real.setScrappedqty(wo.getScrappedqty());
		real.setStartdate(wo.getStartdate());
		real.setWorkorderroutings(wo.getWorkorderroutings());
		
		return real;
	}
}
