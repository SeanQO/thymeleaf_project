package com.sean.taller.dao.imp;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sean.taller.dao.intfcs.ProductSubCategoryDao;
import com.sean.taller.model.prod.Productsubcategory;

@Repository
@Scope("singleton")
@SuppressWarnings("unchecked")
public class ProductSubCategoryDaoImp implements ProductSubCategoryDao{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Productsubcategory update(Productsubcategory psc) {
		em.getTransaction().begin();
		em.merge(psc);
		em.getTransaction().commit();
		em.close();
		return psc;
	}
	
	@Transactional
	@Override
	public void delete(Productsubcategory psc) {
		em.getTransaction().begin();
		em.remove(psc);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Productsubcategory findById(Integer pscId) {
		String jpql = "Select psc from Productsubcategory psc where psc.productsubcategoryid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", pscId);
		
		Productsubcategory psc = null;
		
		try {
			psc = (Productsubcategory) query.getSingleResult();
			
		} catch (NoResultException e) {
			throw new NoResultException();
			
		}
		
		return psc;
	}

	@Override
	public List<Productsubcategory> findAll() {
		String query = "Select psc from Productsubcategory psc";
		List<Productsubcategory> pscs = em.createQuery(query).getResultList();
		
		return pscs;
	}

	@Override
	public List<Productsubcategory> findByCategory(Integer pcId) {
		String jpql = "Select psc from Productsubcategory psc where psc.productcategoryid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", pcId);
		
		List<Productsubcategory> psc = null;
		psc = query.getResultList();

		return psc;
	}

	@Override
	public List<Productsubcategory> findByName(String name) {
		String jpql = "Select psc from Productsubcategory psc where psc.name=:name";
		Query query = em.createQuery(jpql);
		query.setParameter("name", name);
		
		List<Productsubcategory> psc = null;
		psc = query.getResultList();

		return psc;
	}

	@Override
	public Productsubcategory save(Productsubcategory psc) {
		em.getTransaction().begin();
		em.persist(psc);
		em.getTransaction().commit();
		em.close();
		
		return psc;
	}
}
