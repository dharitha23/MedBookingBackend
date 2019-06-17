package com.MediBook.ServiceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MediBook.DataLayer.InterfacesDataLayer.IPatientNotifDL;
import com.MediBook.Model.PatientNotif;
import com.MediBook.ServiceLayer.InterfacesServicelayer.IPatientNotifSL;

@Service
public class PatientNotifService implements IPatientNotifSL	{
@Autowired
	IPatientNotifDL patientNotifDL;
		
		public List<PatientNotif> GetPatientNotif(Integer doctor_id)
		{
			return patientNotifDL.GetPatientNotif(doctor_id);
		}
		

}
