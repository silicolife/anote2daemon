package com.silicolife.anote2daemon.model.dao.daemon;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.GenericDaoImpl;
import com.silicolife.anote2daemon.model.dao.core.daemon.DaemonUserHasDataObjectDao;
import com.silicolife.anote2daemon.model.dao.core.queries.QueriesDao;
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
	public List<Object[]> findQueriesByAttributes(Long id, Long resourceType) {
		
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT b.*, a.* FROM daemon_users_has_data_object AS a "
				+ "INNER JOIN queries as b ON a.uid_resource = b.id "
				+ "WHERE users_id = ? AND type_resources_id = ?";
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, id);
		qry.setParameter(1, resourceType);
		qry.addEntity("daemon_users_has_data_object", DaemonUserHasDataObjectDao.className);
	    qry.addEntity("queries", QueriesDao.className);
		return qry.list();
	}
}
