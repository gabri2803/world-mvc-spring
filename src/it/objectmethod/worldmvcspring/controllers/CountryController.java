package it.objectmethod.worldmvcspring.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.worldmvcspring.dao.ICountryDao;
import it.objectmethod.worldmvcspring.model.Country;

@Controller
public class CountryController {

	@Autowired
	private ICountryDao countryDao;

	@RequestMapping("/continent-list")
	public String listContinent(ModelMap model) {
		List<String> list = null;
		list = countryDao.getAllContinent();
		model.put("continent", list);
		return "show-list";
	}

	@RequestMapping("/country-list")
	public String listCountry(ModelMap model, @RequestParam(name = "nameCont", required = false) String nomeCont,
			HttpSession session) {
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

}
