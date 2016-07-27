package com.company.Gifterland2.service.impl;

import com.company.Gifterland2.dao.GastronomyDao;
import com.company.Gifterland2.model.Gastronomy;
import com.company.Gifterland2.service.GastronomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("gatronomyService")
@Transactional
public class GastronomyServiceImpl implements GastronomyService {

	@Autowired
	private GastronomyDao dao;
	
	public Gastronomy findById(int id) {
		return dao.findById(id);
	}

	public void saveGatronomy(Gastronomy gastronomy) {
		dao.saveGatronomy(gastronomy);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateGatronomy(Gastronomy gastronomy) {
		Gastronomy entity = dao.findById(gastronomy.getId());
		if(entity!=null){
			entity.setName(gastronomy.getName());
			entity.setJoiningDate(gastronomy.getJoiningDate());
			entity.setSalary(gastronomy.getSalary());
			entity.setSsn(gastronomy.getSsn());
		}
	}

	public void deleteGastronomyBySsn(String ssn) {
		dao.deleteGastronomyBySsn(ssn);
	}
	
	public List<Gastronomy> findAllGatronomys() {
		return dao.findAllGastronomies();
	}

	public Gastronomy findGatronomyBySsn(String ssn) {
		return dao.findGastronomyBySsn(ssn);
	}

	public boolean isGastronomySsnUnique(Integer id, String ssn) {
		Gastronomy gastronomy = findGatronomyBySsn(ssn);
		return ( gastronomy == null || ((id != null) && (gastronomy.getId() == id)));
	}
	
}
