package com.sean.taller.services.intfcs;
import java.util.Optional;
import com.sean.taller.model.prod.Workorder;

public interface WorkorderService {
	public Workorder save(Workorder wo);

	public Workorder edit(Workorder wo);
	
	Iterable<Workorder> findAll();

	public Optional<Workorder> findById(Integer id);
}
