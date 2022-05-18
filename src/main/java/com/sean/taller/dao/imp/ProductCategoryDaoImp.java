package com.sean.taller.dao.imp;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
	private EntityManagerFactory e;
	
	@Override
	public Productcategory update(Productcategory pc) {
		EntityManager em = e.createEntityManager();
		em.getTransaction().begin();
		em.merge(pc);
		em.getTransaction().commit();
		em.close();
		return pc;
	}
	
	@Transactional
	@Override
	public void delete(Productcategory pc) {
		EntityManager em = e.createEntityManager();
		em.getTransaction().begin();
		em.remove(pc);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Productcategory findById(Integer pcId) {
		EntityManager em = e.createEntityManager();
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
		EntityManager em = e.createEntityManager();
		String query = "Select pc from Productcategory pc";
		List<Productcategory> pcs = em.createQuery(query).getResultList();
		
		return pcs;
	}

	@Override
	public Productcategory save(Productcategory pc) {
		EntityManager em = e.createEntityManager();
		em.getTransaction().begin();
		em.persist(pc);
		em.getTransaction().commit();
		em.close();
		return pc;
	}
}
