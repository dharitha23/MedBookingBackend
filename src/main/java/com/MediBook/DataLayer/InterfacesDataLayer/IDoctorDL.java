package com.MediBook.DataLayer.InterfacesDataLayer;

import java.util.ArrayList;

import com.MediBook.Model.Comment;
import com.MediBook.Model.Doctor;
import com.MediBook.Model.Patient;

public interface IDoctorDL {
	Doctor getDoctorById(int id);

	ArrayList<Patient> getDoctorPatients(Integer id);

	Object saveAppointmentNotes(Comment comment);

	ArrayList<Comment> getPatientComments(Integer id);

}
