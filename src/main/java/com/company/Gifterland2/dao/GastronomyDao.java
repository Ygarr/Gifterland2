package com.company.Gifterland2.dao;

import com.company.Gifterland2.model.Gastronomy;

import java.util.List;

public interface GastronomyDao {

	Gastronomy findById(int id);

	void saveGastronomy(Gastronomy gastronomy);
	
	void deleteEmployeeBySsn(String ssn);
	
	List<Gastronomy> findAllGastronomies();

	Gastronomy findGastronomyBySsn(String ssn);

}
