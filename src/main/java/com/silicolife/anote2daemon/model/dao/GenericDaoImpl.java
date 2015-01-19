package com.silicolife.anote2daemon.model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.core.GenericDao;

@Repository
public class GenericDaoImpl<T> implements GenericDao<T> {

	private SessionFactory sessionFactory;

	@Autowired
	public GenericDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public T find(Class<T> klass, Serializable id) {
		@SuppressWarnings("unchecked")
		T T = (T) sessionFactory.getCurrentSession().get(klass, id);
		return T;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<T> klass) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		return c.list();
	}

	@Override
	public void save(Object object) {
		sessionFactory.getCurrentSession().save(object);
	}

	@Override
	public void update(Object object) {
		sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public void delete(Object object) {
		sessionFactory.getCurrentSession().delete(object);
	}
}
