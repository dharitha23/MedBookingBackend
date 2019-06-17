package com.MediBook.ServiceLayer.InterfacesServicelayer;

import java.util.List;

import com.MediBook.Model.PatientNotif;

public interface IPatientNotifSL {
	List<PatientNotif> GetPatientNotif(Integer patient_id);
}
