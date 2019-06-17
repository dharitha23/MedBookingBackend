package com.MediBook.ServiceLayer.InterfacesServicelayer;

import java.util.List;

import com.MediBook.Model.DocNotif;

public interface IDocNotifSL {
	List<DocNotif> GetDoctorNotif(Integer doctor_id);
}
