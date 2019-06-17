package com.MediBook.DataLayer.InterfacesDataLayer;

import java.sql.Date;
import java.util.List;

import com.MediBook.Model.ConfirmAppointment;
//Author: Sarmad Noor
//This layer will have interface for data layer.
public interface IConfirmAppointmentDL {
	boolean InsertBookingDetail (ConfirmAppointment appointmentDetail);
	List<ConfirmAppointment> GetDoctorAvailablity (Integer doctor_id, Date appointmentDate);
}
