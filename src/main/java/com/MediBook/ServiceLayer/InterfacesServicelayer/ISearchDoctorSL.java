package com.MediBook.ServiceLayer.InterfacesServicelayer;

import java.util.ArrayList;

import com.MediBook.Model.Doctor;
import com.MediBook.Model.SearchParams;

public interface ISearchDoctorSL {
	Doctor[] SearchDoctorSL ();
	String DbConnectSL ();
	ArrayList<Doctor> searchDoctors(SearchParams searchParams);
}
