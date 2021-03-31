package com.luxoft.springdb.lab2.dao;

import java.util.List;

import com.luxoft.springdb.lab2.model.Country;

public interface CountryDao {

	void save(Country country);

	List<Country> getAllCountries();

	Country getCountryByName(String name);

	Country getCountryByCode(String code);

}