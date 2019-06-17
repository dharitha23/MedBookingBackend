package com.MediBook.ServiceLayer.InterfacesServicelayer;

import java.sql.Date;
import java.util.List;

import com.MediBook.Model.ConfirmAppointment;
//Sarmad Noor
//This layer will have interface for service layer.
public interface IConfirmAppointmentSL {
	boolean InsertBookingDetail (ConfirmAppointment appointmentDetail);
	List<ConfirmAppointment> GetDoctorAvailability(Integer doctor_id, Date appointmentDate);
}
