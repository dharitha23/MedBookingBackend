

package com.MediBook.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.MediBook.DataLayer.InterfacesDataLayer.IDocNotifDL;
import com.MediBook.Model.DocNotif;
import com.MediBook.ServiceLayer.InterfacesServicelayer.IDocNotifSL;


@Service
public class DocNotifService implements IDocNotifSL {

@Autowired
IDocNotifDL docNotifDL;
	
	public List<DocNotif> GetDoctorNotif(Integer doctor_id)
	{
		return docNotifDL.GetDoctorNotif(doctor_id);
	}
	
	

}
