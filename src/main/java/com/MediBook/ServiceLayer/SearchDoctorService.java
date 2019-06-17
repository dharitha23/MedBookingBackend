package com.MediBook.ServiceLayer;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MediBook.DataLayer.InterfacesDataLayer.ISearchDoctorDL;
import com.MediBook.Model.Doctor;
import com.MediBook.Model.SearchParams;
import com.MediBook.ServiceLayer.InterfacesServicelayer.ISearchDoctorSL;

// This layer will have all the business logic for application.
@Service
public class SearchDoctorService implements ISearchDoctorSL {

	@Autowired
	ISearchDoctorDL searchDoctorDL;
	
	public Doctor[] SearchDoctorSL ()
	{
		return searchDoctorDL.SearchDoctorDL();
	}
	
	public String DbConnectSL()
	{
		return searchDoctorDL.DBConnectDL();
	}

	@Override
	public ArrayList<Doctor> searchDoctors(SearchParams searchParams) {
		return searchDoctorDL.searchDoctors(searchParams);
	}

}
