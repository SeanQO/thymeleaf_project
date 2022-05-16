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
	public Workorder add(Workorder wo) {
		
		if(wo.equals(null))
			throw new NullPointerException("Work order does not exist");
		
		if(wo.getOrderqty() < 0) //<========================================
			throw new IllegalArgumentException("Invalid order quantity"); //<========================================
		
		if(wo.getScrappedqty() < 0)
			throw new IllegalArgumentException("Invalid scrapped  quantity");
		
		if(wo.getStartdate().compareTo(wo.getEnddate()) >= 0)
			throw new IllegalArgumentException("Product sell end date is equal or lesser than the sell start date");
		
		wor.save(wo);
		
		return wor.findById(wo.getWorkorderid()).get();
	}

	@Override
	public Workorder edit(Workorder wo) {
		
		Optional<Workorder> deletedWo = wor.findById(wo.getWorkorderid());
		Workorder dw = null;
		
		if (deletedWo.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			dw = deletedWo.get();
		}
		
		if(wo.equals(null))
			throw new NullPointerException("Work order does not exist");
		
		if(wo.getOrderqty() < 0)
			throw new IllegalArgumentException("Invalid order quantity");
		
		if(wo.getScrappedqty() < 0)
			throw new IllegalArgumentException("Invalid scrapped  quantity");
		
		if(wo.getStartdate().compareTo(wo.getEnddate()) >= 0)
			throw new IllegalArgumentException("Product sell end date is equal or lesser than the sell start date");
		
		wor.deleteById(dw.getWorkorderid());
		wor.save(wo);
		
		return wo;
		
	}
	
	@Override
	public Iterable<Workorder> findAll() {
		return wor.findAll();
	}

	@Override
	public Optional<Workorder> findById(Integer id) {
		
		return wor.findById(id);
	}

	@Override
	public void delete(Integer id) {
		wor.deleteById(id);
	}
}
