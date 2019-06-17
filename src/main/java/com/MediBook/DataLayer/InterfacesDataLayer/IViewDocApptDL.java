package com.MediBook.DataLayer.InterfacesDataLayer;

import java.util.List;

import com.MediBook.Model.ViewDoctorAppointment;

public interface IViewDocApptDL
{
List<ViewDoctorAppointment> getAppointmentsUsingDoctorID(Integer doctor_id);
Boolean apptConfirmationByDoc (Integer appt_Id);
}