package com.MediBook.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MediBook.Model.AppointmentsForPatient;
import com.MediBook.ServiceLayer.InterfacesServicelayer.IViewPatientAppointmentSL;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ViewAppointmentsForPatientController {

	@Autowired
	IViewPatientAppointmentSL viewPatientAppointmentSL;

	//Method for returning all the available appointments from the database
	@RequestMapping(value = "/myappointments", method = RequestMethod.GET)
	public List<AppointmentsForPatient> getAppointmentsUsingPatientID(@RequestParam("patient_id") String patient_id) {
		return viewPatientAppointmentSL.getAppointmentsUsingPatientID(Integer.parseInt(patient_id));
	}

	//Method to delete an appointment with an appointment id
	@RequestMapping(value = "/myappointments/delete", method = RequestMethod.DELETE)
	public void deleteByAppointmentID(@RequestParam("appt_id") Integer appt_id) {
		int z= appt_id;
		viewPatientAppointmentSL.deleteByAppointmentID(appt_id);
	}
}
