
package com.MediBook.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.MediBook.Model.DocNotif;
import com.MediBook.ServiceLayer.InterfacesServicelayer.IDocNotifSL;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DocNotifController {

@Autowired
IDocNotifSL DocNotifSL;	

@RequestMapping(value = "/getdocnotif", method = RequestMethod.GET)
public List<DocNotif> GetDoctorNotif(@RequestParam("doctor_id") Integer doctor_id)
{
return DocNotifSL.GetDoctorNotif(doctor_id);
}
}
