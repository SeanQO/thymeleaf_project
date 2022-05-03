package com.sean.taller.services.intfcs;
import com.sean.taller.model.prod.Unitmeasure;

public interface UnitmeasureService {
	public <S extends Unitmeasure> S save(S unitmeasure);
	public Unitmeasure findById(long id);
	public void editUnitmeasure(Unitmeasure um);
	
}
