package it.objectmethod.worldmvcspring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import it.objectmethod.worldmvcspring.dao.ICountryDao;
import it.objectmethod.worldmvcspring.model.Country;
import it.objectmethod.worldmvcspring.model.mapper.CountryMapper;

public class CountryDaoImpl implements ICountryDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public Country getNation(String nameNation, String nameCont) {
		Country country = null;
		String sql = "SELECT * FROM country WHERE Name = ? AND Continent = ?";
		country = this.jdbcTemplateObject.queryForObject(sql, new Object[] { nameNation, nameCont },
				new CountryMapper());
		return country;
	}

	@Override
	public List<Country> getCountriesByContinent(String nameCont) {
		List<Country> country = new ArrayList<>();
		String sql = "SELECT * FROM country WHERE Continent = ?";
		country = this.jdbcTemplateObject.query(sql, new Object[] { nameCont }, new CountryMapper());
		return country;
	}

	@Override
	public List<String> getAllContinent() {
		List<String> list = new ArrayList<>();
		String sql = "SELECT DISTINCT country.Continent FROM country";
		list = this.jdbcTemplateObject.queryForList(sql, String.class);
		return list;
	}

	@Override
	public List<Country> getAllCountry() {
		List<Country> list = new ArrayList<>();
		String sql = "SELECT * FROM country";
		list = this.jdbcTemplateObject.query(sql, new CountryMapper());
		return list;
	}
}
