package com.company.Gifterland2.dao.impl;

import com.company.Gifterland2.dao.AbstractDao;
import com.company.Gifterland2.dao.GastronomyDao;
import com.company.Gifterland2.model.Gastronomy;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gastronomyDao")
public class GastronomyDaoImpl extends AbstractDao<Integer, Gastronomy> implements GastronomyDao {

	public Gastronomy findById(int id) {
		return getByKey(id);
	}

	public void saveGastronomy(Gastronomy gastronomy) {
		persist(gastronomy);
	}

	public void deleteGastronomyBySsn(String ssn) {
		Query query = getSession().createSQLQuery("delete from Gastronomy where ssn = :ssn");
		query.setString("ssn", ssn);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Gastronomy> findAllGastronomy() {
		Criteria criteria = createEntityCriteria();
		return (List<Gastronomy>) criteria.list();
	}

	public Gastronomy findGastronomyBySsn(String ssn) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssn", ssn));
		return (Gastronomy) criteria.uniqueResult();
	}
}
