package com.sean.taller.dao.imp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sean.taller.dao.intfcs.WorkOrderDao;
import com.sean.taller.model.prod.Workorder;

@Repository
@Scope("singleton")
@SuppressWarnings("unchecked")
public class WorkOrderDaoImp implements WorkOrderDao{
	@PersistenceUnit
	private EntityManager em;

	@Override
	public Workorder update(Workorder w) {
		em.merge(w);
		return w;
	}
	
	@Transactional
	@Override
	public void delete(Workorder w) {
		em.remove(w);
	}

	@Override
	public Workorder findById(Integer wId) {
		String jpql = "Select w from Workorder w where w.workorderid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", wId);
		
		Workorder w = null;
		
		try {
			w = (Workorder) query.getSingleResult();
			
		} catch (NoResultException e) {
			throw new NoResultException();
			
		}
		
		return w;
	}

	
	@Override
	public List<Workorder> findAll() {
		String query = "Select w from Workorder w";
		List<Workorder> ws = em.createQuery(query).getResultList();
		
		return ws;
	}

	@Override
	public List<Workorder> findByScrapReason(Integer scrapreasonid) {
		String jpql = "Select w from Workorder w where w.scrapreasonid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", scrapreasonid);
		
		List<Workorder> wos = null;
		wos = query.getResultList();

		return wos;
	}

	@Override
	public List<Workorder> findByQty(Integer orderqty) {
		String jpql = "Select w from Workorder w where w.orderqty=:qty";
		Query query = em.createQuery(jpql);
		query.setParameter("qty", orderqty);
		
		List<Workorder> ws = query.getResultList();
		
		return ws;
	}

	@Override
	public Workorder save(Workorder w) {
		return w;
	}
}
