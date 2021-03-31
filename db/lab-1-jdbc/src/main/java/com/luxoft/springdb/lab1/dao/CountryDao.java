package com.luxoft.springdb.lab1.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.luxoft.springdb.lab1.model.Country;

public class CountryDao extends JdbcDaoSupport {
	private static final CountryRowMapper COUNTRY_ROW_MAPPER = new CountryRowMapper();

	private final NamedParameterJdbcOperations namedJdbc;
	private final JdbcOperations jdbc;

	public CountryDao(NamedParameterJdbcOperations namedJdbc) {
		this.namedJdbc = namedJdbc;
		this.jdbc = namedJdbc.getJdbcOperations();
	}

	public List<Country> getCountryList() {
		return jdbc.query("select * from country", COUNTRY_ROW_MAPPER);
	}

	public List<Country> getCountryListStartWith(String name) {
		return namedJdbc.query(
				"select * from country where name like :name",
				new MapSqlParameterSource("name", name + "%"),
				COUNTRY_ROW_MAPPER);
	}

	public void updateCountryName(String codeName, String newCountryName) {
		namedJdbc.update("update country SET name = :newCountryName where code_name = :codeName",
				new MapSqlParameterSource()
						.addValue("newCountryName", newCountryName)
						.addValue("codeName", codeName));
	}

	public Country getCountryByCodeName(String codeName) {
		return namedJdbc.query(
				"select * from country where code_name = :codeName",
				new MapSqlParameterSource("codeName", codeName),
				COUNTRY_ROW_MAPPER).get(0);
	}

	public Country getCountryByName(String name)
			throws CountryNotFoundException {
		List<Country> countryList = namedJdbc.query(
				"select * from country where name = :name",
				new MapSqlParameterSource("name", name),
				COUNTRY_ROW_MAPPER);
		if (countryList.isEmpty()) {
			throw new CountryNotFoundException();
		}
		return countryList.get(0);
	}
}
