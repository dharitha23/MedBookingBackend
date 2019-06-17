package com.MediBook.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MediBook.Model.Doctor;
import com.MediBook.Model.SearchParams;
import com.MediBook.ServiceLayer.InterfacesServicelayer.ISearchDoctorSL;

//This layer will have logic for handling the HTTP requests for application.
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class SearchDoctorController {
	@Autowired
	ISearchDoctorSL searchDoctorSL;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	ArrayList<Doctor> SearchDoctorCtrl(@RequestParam("latitude") String latitude,
			@RequestParam("searchText") String searchText, @RequestParam("longitude") String longitude,
			@RequestParam("speciality") String speciality) {
		SearchParams searchParams = new SearchParams(latitude, longitude, speciality, searchText);
		return searchDoctorSL.searchDoctors(searchParams);
	}

	@RequestMapping("/demo")
	public String DbConnectCtrl() {
		return searchDoctorSL.DbConnectSL();
	}
}
