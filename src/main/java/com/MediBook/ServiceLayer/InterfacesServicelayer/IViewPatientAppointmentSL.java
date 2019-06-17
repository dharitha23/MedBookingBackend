package com.MediBook.ServiceLayer.InterfacesServicelayer;

import java.util.List;

import com.MediBook.Model.AppointmentsForPatient;

public interface IViewPatientAppointmentSL {

	//Interfaces that would be implemented in the IViewPatientAppointmentSL class
	List<AppointmentsForPatient> getAppointmentsUsingPatientID(int patientID);
	public void deleteByAppointmentID(Integer appt_id);

}
