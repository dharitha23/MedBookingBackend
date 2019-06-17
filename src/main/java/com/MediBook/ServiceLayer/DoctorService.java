package com.MediBook.ServiceLayer;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MediBook.DataLayer.InterfacesDataLayer.IDoctorDL;
import com.MediBook.Model.Comment;
import com.MediBook.Model.Doctor;
import com.MediBook.Model.Patient;
import com.MediBook.ServiceLayer.InterfacesServicelayer.IDoctorSL;

//This layer will have all the business logic for application.
@Service
public class DoctorService implements IDoctorSL {

	@Autowired
	IDoctorDL doctorDL;

	@Override
	public Doctor getDoctorById(int id) {

		return doctorDL.getDoctorById(id);
	}

	@Override
	public ArrayList<Patient> getDoctorPatients(Integer id) {
		return doctorDL.getDoctorPatients(id);

	}

	@Override
	public Object saveAppointmentNotes(Comment comment) {
		return doctorDL.saveAppointmentNotes(comment);

	}

	@Override
	public ArrayList<Comment> getPatientComments(Integer id) {
		return doctorDL.getPatientComments(id);
	}
}
