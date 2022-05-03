package com.sean.taller.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sean.taller.model.prod.Unitmeasure;
import com.sean.taller.repository.UnitmeasureRepository;
import com.sean.taller.services.intfcs.UnitmeasureService;
@Service
public class UnitmeasureServiceImp implements UnitmeasureService {
	
	@Autowired
	private UnitmeasureRepository umr;
	
	@Override
	public <S extends Unitmeasure> S save(S um) {
		return umr.save(um);
		
	}
	
	@Override
	public Unitmeasure findById(long id) {
		return umr.findById(id).get();
	}

	@Override
	public void editUnitmeasure(Unitmeasure um) {
		Unitmeasure umEntity = findById(um.getUnitmeasurecode());
		umEntity.setName(um.getName());
		save(umEntity);
		
	}
	
}
