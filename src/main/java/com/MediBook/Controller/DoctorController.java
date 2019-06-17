package com.MediBook.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MediBook.Model.Comment;
import com.MediBook.Model.Doctor;
import com.MediBook.Model.DoctorProfile;
import com.MediBook.Model.NewUser;
import com.MediBook.Model.Patient;
import com.MediBook.ServiceLayer.InterfacesServicelayer.IDoctorSL;

//This layer will have logic for handling the HTTP requests for application.
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class DoctorController {

	@Autowired
	IDoctorSL doctorSL;

	@RequestMapping(value = "/doctor", method = RequestMethod.GET)
	public Doctor getDoctorById(@RequestParam("id") Integer id) {
		return doctorSL.getDoctorById(id);
	}

	@RequestMapping(value = "/mypatients", method = RequestMethod.GET)
	public ArrayList<Patient> getDoctorPatients(@RequestParam("id") Integer id) {
		return doctorSL.getDoctorPatients(id);
	}

	@RequestMapping(value = "/saveapptnotes", method = RequestMethod.POST)
	public Object saveAppointmentNotes(@RequestBody Comment comment) {
		return this.doctorSL.saveAppointmentNotes(comment);
	}
	
	@RequestMapping(value = "/patientcomments", method = RequestMethod.GET)
	public ArrayList<Comment> getPatientComments(@RequestParam("id") Integer id) {
		return doctorSL.getPatientComments(id);
	}
	
	

}
