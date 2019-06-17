package com.MediBook.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MediBook.DataLayer.InterfacesDataLayer.PatientSignupIDL;
import com.MediBook.Model.PatientForRating;
import com.MediBook.Model.PatientProfile;
import com.MediBook.Model.Rating;
import com.MediBook.ServiceLayer.InterfacesServicelayer.PatientSignupISL;


@Service
public class PatientSignupSL implements PatientSignupISL {
	@Autowired
	PatientSignupIDL patientsignupDL;
		public void addPatient(PatientProfile patient)
		{
			//System.out.println("in PatientSignupSL addPatient");
			patientsignupDL.addPatient(patient);
		} 
		
		public void setPatDetails(PatientProfile patient) {
			patientsignupDL.setPatDetails(patient);
		}
		
		public PatientProfile getPatDetails(String email) {
			return patientsignupDL.getPatDetails(email);
		}
		
		public PatientForRating getPatient(String email) {
			return patientsignupDL.getPatient(email);
		}

		public void setRating (Rating rating) {
			patientsignupDL.setRating(rating);
		}

}
