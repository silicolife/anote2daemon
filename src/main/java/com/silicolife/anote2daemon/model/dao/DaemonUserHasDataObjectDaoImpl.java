package com.silicolife.anote2daemon.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.core.DaemonUserHasDataObjectDao;
import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObject;
@Repository
public class DaemonUserHasDataObjectDaoImpl extends GenericDaoImpl<DaemonUsersHasDataObject> implements DaemonUserHasDataObjectDao {
	private SessionFactory sessionFactory;

	@Autowired
	public DaemonUserHasDataObjectDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findObjectsByAttributes(Long id, Long resourceType) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT * FROM daemon_users_has_data_object WHERE users_id = ? AND type_resources_id = ?";
		Query qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, id);
		qry.setParameter(1, resourceType);
		qry.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return qry.list();
	}
}
