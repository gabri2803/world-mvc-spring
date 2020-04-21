package it.objectmethod.worldmvcspring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import it.objectmethod.worldmvcspring.dao.ICityDao;
import it.objectmethod.worldmvcspring.model.City;

public class CityDaoImpl extends NamedParameterJdbcDaoSupport implements ICityDao {

	@Override
	public City getCityByName(String nameCity) {
		City city = null;
		String sql = "SELECT * FROM city WHERE Name = ?";
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		city = getJdbcTemplate().queryForObject(sql, new Object[] { nameCity }, rm);
		return city;
	}

	@Override
	public List<City> getCitiesByCountryCode(String countryCode) {
		List<City> list = new ArrayList<>();
		String sql = "SELECT * FROM city WHERE city.CountryCode= ? ";
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		list = getJdbcTemplate().query(sql, new Object[] { countryCode }, rm);
		return list;
	}

	@Override
	public List<City> getCityByNameOrByCountry(String cityName, String country) {
		List<City> list = new ArrayList<>();
		String sql = "SELECT *, city.name name, city.population population FROM city JOIN country ON city.CountryCode = country.Code WHERE city.Name LIKE :nomeInserito "
				+ "AND (:codiceInserito = '' OR country.Code= :codiceInserito) LIMIT 10";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("nomeInserito", "%" + cityName + "%");
		params.addValue("codiceInserito", country);
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		list = getNamedParameterJdbcTemplate().query(sql, params, rm);
		return list;
	}

	@Override
	public void postNewCity(City city) {
		String sql = "INSERT INTO city (name, countryCode, district, population) VALUES (:nomeIns, :codiceIns, :distIns, :popIns);";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("nomeIns", city.getName());
		params.addValue("codiceIns", city.getCountryCode());
		params.addValue("distIns", city.getDistrict());
		params.addValue("popIns", city.getPopulation());
		getNamedParameterJdbcTemplate().update(sql, params);
	}

	@Override
	public void putCity(City city) {
		String sql = "UPDATE city SET name= :nomeIns , countryCode= :codiceIns, district = :distIns, population = :popIns WHERE ID= :id ";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("nomeIns", city.getName());
		params.addValue("codiceIns", city.getCountryCode());
		params.addValue("distIns", city.getDistrict());
		params.addValue("popIns", city.getPopulation());
		params.addValue("id", city.getId());
		getNamedParameterJdbcTemplate().update(sql, params);
	}

	@Override
	public City getCityById(int id) {
		City city = null;
		String sql = "SELECT * FROM city WHERE ID = ?";
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		city = getJdbcTemplate().queryForObject(sql, new Object[] { id }, rm);
		return city;
	}

}
