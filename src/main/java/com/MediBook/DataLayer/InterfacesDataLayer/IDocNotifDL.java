package com.MediBook.DataLayer.InterfacesDataLayer;

import java.util.List;

import com.MediBook.Model.DocNotif;

public interface IDocNotifDL {
	List<DocNotif> GetDoctorNotif (Integer doctor_id);
}

