package com.sean.taller.services.intfcs;

import com.sean.taller.model.prod.Workorder;

public interface WorkorderService {
	public Workorder save(Workorder wo);

	public Workorder edit(Workorder wo);
	
}