package com.MediBook.ServiceLayer.InterfacesServicelayer;

import java.util.ArrayList;

import com.MediBook.Model.Comment;
import com.MediBook.Model.Doctor;
import com.MediBook.Model.Patient;

public interface IDoctorSL {
	Doctor getDoctorById(int id);

	ArrayList<Patient>  getDoctorPatients(Integer id);
	
	Object saveAppointmentNotes(Comment comment);

	ArrayList<Comment> getPatientComments(Integer id);

}
