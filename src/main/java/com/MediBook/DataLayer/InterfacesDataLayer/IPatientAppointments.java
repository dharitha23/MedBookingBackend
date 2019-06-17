package com.MediBook.DataLayer.InterfacesDataLayer;

import java.util.List;

import com.MediBook.Model.AppointmentsForPatient;

public interface IPatientAppointments {

	//Interfaces that would be implemented in the ViewPatientAppointmentsDL class
	List<AppointmentsForPatient> getAppointmentsUsingPatientID(int patientID);
	public void deleteByAppointmentID(Integer appt_id);
}
