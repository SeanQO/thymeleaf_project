package com.sean.taller.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.sean.taller.dao.intfcs.Queries;
import com.sean.taller.model.prod.Product;
import com.sean.taller.model.prod.Productsubcategory;

@Repository
@Scope("singleton")
@SuppressWarnings("unchecked")
public class QueriesImp implements Queries{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Productsubcategory> findProdCategByProdSubCateg() {
		String jpql = "";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public List<Product> findProductByWorkOrder() {
		String jpql = "";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

}
