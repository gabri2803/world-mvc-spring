package it.objectmethod.worldmvcspring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.worldmvcspring.dao.ICityDao;
import it.objectmethod.worldmvcspring.dao.ICountryDao;
import it.objectmethod.worldmvcspring.model.City;
import it.objectmethod.worldmvcspring.model.Country;

@Controller
public class CityController {

	@Autowired
	private ICountryDao countryDao;
	@Autowired
	private ICityDao cityDao;

	@RequestMapping("/search-by")
	public String searchBy(ModelMap model, @RequestParam(name = "name", required = false) String cityName,
			@RequestParam(name = "country", required = false) String country) {
		List<Country> list = null;

		list = countryDao.getAllCountry();

		List<City> cities = null;

		if (cityName == null) {
			cityName = "";
		}
		if (country == null) {
			country = "";
		}
		cities = cityDao.getCityByNameOrByCountry(cityName, country);
		model.put("countryList", list);
		model.put("cityList", cities);
		return "search-city";
	}

	@RequestMapping("/list-city")
	public String listCities(ModelMap model, @RequestParam("code") String countryCode) {
		List<City> list = new ArrayList<>();
		list = cityDao.getCitiesByCountryCode(countryCode);
		model.put("cityList", list);
		return "show-list-cities";
	}

	@RequestMapping("/page-insert")
	public String editPage(ModelMap model, @RequestParam(name = "id", required = false) int id) {
		List<Country> list = null;
		City city = null;
		if (id > -1) {
			city = cityDao.getCityById(id);
		}
		list = countryDao.getAllCountry();
		model.put("city", city);
		model.put("countryList", list);
		return "edit-city";
	}

	@RequestMapping("/insert")
	public String editCity(ModelMap model, @RequestParam(name = "idCity", required = false) String idCity,
			@RequestParam("name") String name, @RequestParam("code") String countryCode,
			@RequestParam("dist") String district, @RequestParam("pop") int population) {
		String message = "Modifica avvenuta con successo.";
		City city = new City();
		city.setName(name);
		city.setCountryCode(countryCode);
		city.setDistrict(district);
		city.setPopulation(population);
		if (idCity != "") {
			int id = Integer.parseInt(idCity);
			city.setId(id);
			cityDao.putCity(city);
		} else {
			cityDao.postNewCity(city);
		}
		listCities(model, countryCode);
		model.put("message", message);
		return "show-list-cities";
	}
}
