package com.MediBook.Controller;

import com.MediBook.ServiceLayer.InterfacesServicelayer.IViewDocApptSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.MediBook.Model.ConfirmAppointment;
import com.MediBook.Model.ViewDoctorAppointment;

//Author: Sarmad Noor
//This layer will have logic for handling the HTTP requests for application.
@CrossOrigin
@RestController
public class ViewDocApptController {

	@Autowired
	IViewDocApptSL viewDocApptSL;

	@RequestMapping(value = "/docmyappointments", method = RequestMethod.GET)
	public List<ViewDoctorAppointment> getAppointmentsUsingDoctorID(@RequestParam("doctor_id") String doctor_id) {
		return viewDocApptSL.getAppointmentsUsingDoctorID(Integer.parseInt(doctor_id));
	}

	@RequestMapping(value = "/DoctorConfirmation", method = RequestMethod.POST)
	public boolean apptConfirmationByDoc(@RequestBody Integer appt_Id) {
		return viewDocApptSL.apptConfirmationByDoc(appt_Id);
	}
}