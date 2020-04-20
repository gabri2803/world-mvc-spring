package it.objectmethod.worldmvcspring.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import it.objectmethod.worldmvcspring.model.City;

@Component
public interface ICityDao {

	City getCityByName(String nameCity);

	List<City> getCitiesByCountryCode(String countryCode);

	List<City> getCityByNameOrByCountry(String cityName, String country);

	void postNewCity(City city);

	void putCity(City city);

	City getCityById(int id);
}
