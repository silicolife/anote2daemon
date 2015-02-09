package com.silicolife.anote2daemon.model.core.dao.specialdao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.entities.Queries;
import com.silicolife.anote2daemon.model.core.entities.UsersHasDataObject;

@Repository
public class QueriesAuxDaoImpl implements QueriesAuxDao{
	private SessionFactory sessionFactory;

	@Autowired
	public QueriesAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Object[]> findQueriesByAttributes(Long id, String resourceType) {
		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT b.*, a.* FROM users_has_data_object AS a "
				+ "INNER JOIN queries as b ON a.uid_resource = b.id "
				+ "WHERE users_id = ? AND type_resources_id = ?";
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, id);
		qry.setParameter(1, resourceType);
		qry.addEntity("users_has_data_object", UsersHasDataObject.class);
	    qry.addEntity("queries", Queries.class);
	    @SuppressWarnings("unchecked")
		List<Object[]>  result = qry.list();
	    if(result.size() == 0)
	    	return null;
	    
		return result;
	}

}
