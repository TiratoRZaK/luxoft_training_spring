package com.luxoft.springdb.lab1.dao;

import org.springframework.jdbc.core.RowMapper;

import com.luxoft.springdb.lab1.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {

	public Country mapRow(ResultSet resultSet, int i) throws SQLException {
		Country country = new Country();
		country.setId(resultSet.getInt("id"));
		country.setName(resultSet.getString("name"));
		country.setCodeName(resultSet.getString("code_name"));
		return country;
	}
}
