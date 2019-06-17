package com.MediBook.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import com.MediBook.DataLayer.InterfacesDataLayer.IConfirmAppointmentDL;
import com.MediBook.Model.ConfirmAppointment;
import com.MediBook.ServiceLayer.InterfacesServicelayer.IConfirmAppointmentSL;


@Service
public class ConfirmAppointmentService implements IConfirmAppointmentSL {

@Autowired
IConfirmAppointmentDL confirmAppointmentDL;
	public boolean InsertBookingDetail(ConfirmAppointment appointmentDetail)
	{		
		return confirmAppointmentDL.InsertBookingDetail(appointmentDetail);
	} 
	public List<ConfirmAppointment> GetDoctorAvailability(Integer doctor_id, Date appointmentDate)
	{
		return confirmAppointmentDL.GetDoctorAvailablity(doctor_id, appointmentDate);
	}
	
	

}
