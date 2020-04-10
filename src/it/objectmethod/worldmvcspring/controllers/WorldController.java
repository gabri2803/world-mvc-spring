package it.objectmethod.worldmvcspring.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.worldmvcspring.dao.ICityDao;
import it.objectmethod.worldmvcspring.dao.ICountryDao;
import it.objectmethod.worldmvcspring.domain.City;
import it.objectmethod.worldmvcspring.domain.Country;

@Controller
public class WorldController {

	@Autowired
	private ICountryDao countryDao;
	@Autowired
	private ICityDao cityDao;
	@Autowired
	private HttpSession session;

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

	@RequestMapping("/continent-list")
	public String listContinent(ModelMap model) {
		List<String> list = null;
		list = countryDao.getAllContinent();
		model.put("continent", list);
		return "show-list";
	}

	@RequestMapping("/country-list")
	public String listCountry(ModelMap model, @RequestParam(name = "nameCont", required = false) String nomeCont) {
		if (nomeCont != null) {
			session.setAttribute("nameCont", nomeCont);
		} else {
			nomeCont = (String) session.getAttribute("nameCont");
		}

		List<Country> countryList = null;
		countryList = countryDao.getCountriesByContinent(nomeCont);
		model.put("countryList", countryList);
		return "show-list-countries";
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
		if (idCity != "") {
			int id = Integer.parseInt(idCity);
			cityDao.putCity(id, name, countryCode, district, population);
		} else {
			cityDao.postNewCity(name, countryCode, district, population);
		}
		listCities(model, countryCode);
		return "show-list-cities";
	}
}
