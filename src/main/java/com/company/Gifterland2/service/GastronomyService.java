package com.company.Gifterland2.service;

import com.company.Gifterland2.model.Gastronomy;

import java.util.List;

public interface GastronomyService {

	Gastronomy findById(int id);
	
	void saveGastronomy(Gastronomy gastronomy);
	
	void updateGastronomy(Gastronomy gastronomy);
	
	void deleteGastronomyBySsn(String ssn);

	List<Gastronomy> findAllGastronomies();
	
	Gastronomy findGastronomyBySsn(String ssn);

	boolean isGastronomySsnUnique(Integer id, String ssn);
	
}
