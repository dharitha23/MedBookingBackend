package com.MediBook.DataLayer.InterfacesDataLayer;

import java.util.List;

import com.MediBook.Model.PatientNotif;

public interface IPatientNotifDL {
	List<PatientNotif> GetPatientNotif(Integer patient_id);
}
