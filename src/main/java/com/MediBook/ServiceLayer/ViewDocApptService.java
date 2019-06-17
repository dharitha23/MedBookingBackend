package com.MediBook.ServiceLayer;

import java.net.Inet4Address;
import java.util.List;

import com.MediBook.DataLayer.InterfacesDataLayer.IViewDocApptDL;
import com.MediBook.Model.ViewDoctorAppointment;
import com.MediBook.ServiceLayer.InterfacesServicelayer.IViewDocApptSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewDocApptService implements IViewDocApptSL {
    @Autowired
    IViewDocApptDL viewDocApptDL;

    public List<ViewDoctorAppointment> getAppointmentsUsingDoctorID(Integer doctor_id) {
        return viewDocApptDL.getAppointmentsUsingDoctorID(doctor_id);
    }
    public Boolean apptConfirmationByDoc (Integer appt_Id)
    {
        return viewDocApptDL.apptConfirmationByDoc(appt_Id);
    }



    
}
