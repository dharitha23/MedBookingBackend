package com.MediBook.DataLayer.InterfacesDataLayer;

import java.util.ArrayList;

import com.MediBook.Model.Doctor;
import com.MediBook.Model.SearchParams;

public interface ISearchDoctorDL {
	 Doctor[] SearchDoctorDL ();
	 String DBConnectDL();
	ArrayList<Doctor> searchDoctors(SearchParams searchParams);

}
