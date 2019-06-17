package com.MediBook.ServiceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MediBook.DataLayer.InterfacesDataLayer.IPatientAppointments;
import com.MediBook.Model.AppointmentsForPatient;
import com.MediBook.ServiceLayer.InterfacesServicelayer.IViewPatientAppointmentSL;

@Service
public class ViewPatientAppointmentService implements IViewPatientAppointmentSL {

	@Autowired
	IPatientAppointments viewPatientAppointmentDL;

	//Implementation for returning all the available appointments from the database
	@Override
	public List<AppointmentsForPatient> getAppointmentsUsingPatientID(int patientID) {
		// TODO Auto-generated method stub
		return viewPatientAppointmentDL.getAppointmentsUsingPatientID(patientID);
	}

	//Implementation to delete an appointment with an appointment id
	public void deleteByAppointmentID(Integer appt_id) {
		viewPatientAppointmentDL.deleteByAppointmentID(appt_id);
	}
}
