package com.MediBook.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.MediBook.Model.ConfirmAppointment;
import com.MediBook.ServiceLayer.InterfacesServicelayer.IConfirmAppointmentSL;
//Author: Sarmad Noor
//This layer will have logic for handling the HTTP requests for application.
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ConfirmAppointmentController {

	@Autowired
	IConfirmAppointmentSL confirmappointmentSL;

	@RequestMapping(value = "/confirmappointment", method = RequestMethod.POST)
	public boolean InsertAppointmentDetail(@RequestBody ConfirmAppointment appointmentDetail) {
		appointmentDetail.appointmentDate = ConvertToSQLDate(appointmentDetail.appt_Date);
		return confirmappointmentSL.InsertBookingDetail(appointmentDetail);

	}

	@RequestMapping(value = "/getavailability", method = RequestMethod.GET)
	public List<ConfirmAppointment> GetDoctorAvailability(@RequestParam("doctor_id") Integer doctor_id,
			@RequestParam("appointmentDate") Date appointmentDate) {
		return confirmappointmentSL.GetDoctorAvailability(doctor_id, appointmentDate);
	}

	// https://javarevisited.blogspot.com/2012/04/how-to-convert-local-time-to-gmt-in.html
	private java.sql.Date ConvertToSQLDate(String bookdate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 java.util.Date bDate = null;
		try {
			bDate = dateFormat.parse(bookdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sBookingDate = new java.sql.Date(bDate.getTime());
	return sBookingDate;
}
}
