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

import com.sean.taller.dao.intfcs.ProductDao;
import com.sean.taller.model.prod.Product;

@SuppressWarnings("unchecked")
@Repository
@Scope("singleton")
public class ProductDaoImp implements ProductDao{
	
	@PersistenceUnit
	private EntityManagerFactory e;

	@Override
	public Product update(Product p) {
		EntityManager em = e.createEntityManager();
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		em.close();
		return p;
	}
	
	@Transactional
	@Override
	public void delete(Product p) {
		EntityManager em = e.createEntityManager();
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Product findById(Integer pId) {
		EntityManager em = e.createEntityManager();
		String jpql = "Select p from Product p where p.productid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", pId);
		
		Product p = null;
		
		try {
			p = (Product) query.getSingleResult();
			
		} catch (NoResultException e) {
			throw new NoResultException();
			
		}
		
		return p;

	}

	@Override
	public List<Product> findAll() {
		EntityManager em = e.createEntityManager();
		String query = "Select p from Product p";
		List<Product> ps = em.createQuery(query).getResultList();
		
		return ps;
	}

	
	@Override
	public List<Product> findBySubCategory(Integer productsubcategoryid) {
		EntityManager em = e.createEntityManager();
		String jpql = "Select p from Product p where p.productsubcategoryid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", productsubcategoryid);
		
		List<Product> psc = null;
		psc = query.getResultList();

		return psc;
	}
	
	@Override
	public List<Product> findByUnitMeasure(Long unitmeasurecode) {
		return null;
	}
/*
	@Override
	public List<Product> findByUnitMeasure(Long unitmeasurecode) {
		String jpql = "Select p from Product p where p.productsubcategoryid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", productsubcategoryid);
		
		List<Product> psc = null;
		psc = query.getResultList();

		return psc;
	}
*/
	@Override
	public Product save(Product p) {
		EntityManager em = e.createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		return p;
	}
}
