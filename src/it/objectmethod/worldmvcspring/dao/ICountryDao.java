package it.objectmethod.worldmvcspring.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import it.objectmethod.worldmvcspring.model.Country;

@Component
public interface ICountryDao {
	Country getNation(String nameNation, String nameCont);

	List<Country> getCountriesByContinent(String nameCont);

	List<String> getAllContinent();

	List<Country> getAllCountry();

}
