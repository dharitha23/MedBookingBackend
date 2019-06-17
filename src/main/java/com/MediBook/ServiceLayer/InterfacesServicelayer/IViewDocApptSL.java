package com.MediBook.ServiceLayer.InterfacesServicelayer;

import java.util.List;

import com.MediBook.Model.ViewDoctorAppointment;

public interface IViewDocApptSL {
    List <ViewDoctorAppointment> getAppointmentsUsingDoctorID (Integer doctor_id);
    Boolean apptConfirmationByDoc(Integer appt_Id); 
}