package com.luxoft.springdb.lab2.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import com.luxoft.springdb.lab2.dao.CountryDao;
import com.luxoft.springdb.lab2.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

	@Override
	public void save(Country country) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(country);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Country> getAllCountries() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Country> countryList = entityManager.createQuery("from Country", Country.class)
				.getResultList();
		entityManager.getTransaction().commit();

		return countryList;
	}

	@Override
	public Country getCountryByName(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Country country = entityManager.createQuery("select c from Country c where c.name like ?1", Country.class)
				.setParameter(1, name)
				.getSingleResult();
		entityManager.getTransaction().commit();
		return country;
	}

	@Override
	public Country getCountryByCode(String code) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Country country = entityManager.createQuery("select c from Country c where c.codeName like ?1", Country.class)
				.setParameter(1, code)
				.getSingleResult();
		entityManager.getTransaction().commit();
		return country;
	}

}
