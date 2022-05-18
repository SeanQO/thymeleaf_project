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

import com.sean.taller.dao.intfcs.WorkOrderDao;
import com.sean.taller.model.prod.Workorder;

@Repository
@Scope("singleton")
@SuppressWarnings("unchecked")
public class WorkOrderDaoImp implements WorkOrderDao{
	@PersistenceUnit
	private EntityManagerFactory e;

	@Override
	public Workorder update(Workorder w) {
		EntityManager em = e.createEntityManager();
		em.getTransaction().begin();
		em.merge(w);
		em.getTransaction().commit();
		em.close();
		return w;
	}
	
	@Transactional
	@Override
	public void delete(Workorder w) {
		EntityManager em = e.createEntityManager();
		em.getTransaction().begin();
		em.remove(w);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Workorder findById(Integer wId) {
		EntityManager em = e.createEntityManager();
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
		EntityManager em = e.createEntityManager();
		String query = "Select w from Workorder w";
		List<Workorder> ws = em.createQuery(query).getResultList();
		
		return ws;
	}

	@Override
	public List<Workorder> findByScrapReason(Integer scrapreasonid) {
		EntityManager em = e.createEntityManager();
		String jpql = "Select w from Workorder w where w.scrapreasonid=:id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", scrapreasonid);
		
		List<Workorder> wos = null;
		wos = query.getResultList();

		return wos;
	}

	@Override
	public List<Workorder> findByQty(Integer orderqty) {
		EntityManager em = e.createEntityManager();
		String jpql = "Select w from Workorder w where w.orderqty=:qty";
		Query query = em.createQuery(jpql);
		query.setParameter("qty", orderqty);
		
		List<Workorder> ws = query.getResultList();
		
		return ws;
	}

	@Override
	public Workorder save(Workorder w) {
		EntityManager em = e.createEntityManager();
		em.getTransaction().begin();
		em.persist(w);
		em.getTransaction().commit();
		em.close();
		return w;
	}
}
