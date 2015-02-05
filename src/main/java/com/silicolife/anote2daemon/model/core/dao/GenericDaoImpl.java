package com.silicolife.anote2daemon.model.core.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDaoImpl<T> implements GenericDao<T> {

	private SessionFactory sessionFactory;

	@Autowired
	public GenericDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public T findById(Class<T> klass, Serializable id) {
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
	public T findUniqueByAttribute(Class<T> klass, String attribute, Serializable value) {
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put(attribute, value);
		List<T> result = findByAttributes(klass, eqRestrictions);
		if (result.size() == 1)
			return result.get(0);

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByAttributes(Class<T> klass, Map<String, Serializable> eqRestrictions) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		for (Map.Entry<String, Serializable> entry : eqRestrictions.entrySet())
			c.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByOrAttributes(Class<T> klass, Map<String, Serializable> orRestrictions) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(klass);
		Disjunction or = Restrictions.disjunction();
		for (Map.Entry<String, Serializable> entry : orRestrictions.entrySet())
			or.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		c.add(or);
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
