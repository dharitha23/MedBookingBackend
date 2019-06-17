
package com.MediBook.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.MediBook.Model.PatientNotif;
import com.MediBook.ServiceLayer.InterfacesServicelayer.IPatientNotifSL;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PatientNotifController {

@Autowired
IPatientNotifSL PatientNotifSL;	

@RequestMapping(value = "/getpatientnotif", method = RequestMethod.GET)
public List<PatientNotif> GetPatientNotif(@RequestParam("patient_id") Integer patient_id)
{
return PatientNotifSL.GetPatientNotif(patient_id);
}
}

