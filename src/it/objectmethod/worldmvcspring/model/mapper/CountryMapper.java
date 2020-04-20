package it.objectmethod.worldmvcspring.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.objectmethod.worldmvcspring.model.Country;

public class CountryMapper implements RowMapper<Country> {

	@Override
	public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
		Country country = new Country();
		country.setCode(rs.getString("Code"));
		country.setName(rs.getString("Name"));
		country.setContinent(rs.getString("Continent"));
		country.setRegion(rs.getString("Region"));
		country.setSurfaceArea(rs.getDouble("SurfaceArea"));
		country.setIndepYear(rs.getInt("IndepYear"));
		country.setPopulation(rs.getInt("Population"));
		country.setLifeExpectancy(rs.getDouble("LifeExpectancy"));
		country.setGnp(rs.getDouble("GNP"));
		country.setGnpOld(rs.getDouble("GNPOld"));
		country.setLocalName(rs.getString("LocalName"));
		country.setGovernmentForm(rs.getString("GovernmentForm"));
		country.setHeadOfState(rs.getString("HeadOfState"));
		country.setCapital(rs.getInt("Capital"));
		country.setSecondCode(rs.getString("Code2"));
		return country;

	}
}
