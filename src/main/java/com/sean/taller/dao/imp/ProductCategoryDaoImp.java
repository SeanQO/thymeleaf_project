package com.sean.taller.dao.imp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.sean.taller.dao.intfcs.ProductCategoryDao;
import com.sean.taller.model.prod.Productcategory;

@Repository
@Scope("singleton")
public class ProductCategoryDaoImp implements ProductCategoryDao{
	@PersistenceUnit
	private EntityManager em;
	
	@Override
	public Productcategory update(Productcategory pc) {
		em.merge(pc);
		return pc;
	}
	
	@Transactional
	@Override
	public void delete(Productcategory pc) {
		em.remove(pc);
	}

	@Override
	public Productcategory findById(Integer pcId) {
		String jpql = "Select pc from Productcategory pc where pc.productcategoryid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", pcId);
		
		Productcategory pc = null;
		
		try {
			pc = (Productcategory) query.getSingleResult();
			
		} catch (NoResultException e) {
			throw new NoResultException();
			
		}
		
		return pc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Productcategory> findAll() {
		String query = "Select pc from Productcategory pc";
		List<Productcategory> pcs = em.createQuery(query).getResultList();
		
		return pcs;
	}

	@Override
	public Productcategory save(Productcategory pc) {
		em.persist(pc);
		return pc;
	}
}
