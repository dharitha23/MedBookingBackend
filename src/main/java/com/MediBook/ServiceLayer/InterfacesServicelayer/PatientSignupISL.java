package com.MediBook.ServiceLayer.InterfacesServicelayer;

import java.util.List;

import com.MediBook.Model.PatientForRating;
import com.MediBook.Model.PatientProfile;
import com.MediBook.Model.Rating;

public interface PatientSignupISL {
	void addPatient (PatientProfile patient);
	//List<Patient> getPatients();
	void setPatDetails (PatientProfile patient);
	PatientProfile getPatDetails (String email);
	PatientForRating getPatient (String email);
	void setRating (Rating rating);
}
