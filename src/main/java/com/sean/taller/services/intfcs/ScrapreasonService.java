package com.sean.taller.services.intfcs;

import com.sean.taller.model.prod.Scrapreason;

public interface ScrapreasonService {
	public Scrapreason save(Scrapreason sr);
	
	public Scrapreason edit(Integer id);
}
