package com.MediBook.DataLayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.MediBook.DataLayer.InterfacesDataLayer.IPatientNotifDL;
import com.MediBook.Model.PatientNotif;

@Repository
public class PatientNotifDL implements IPatientNotifDL {
	@Autowired
	private Environment envr;
	
	public List<PatientNotif> GetPatientNotif(Integer patient_id)
	{
	Connection con = null;
	CallableStatement callStatement = null;
	String url = envr.getProperty("connectionstring");
	PatientNotif notif = null; 
	List <PatientNotif> lstnotif = null;
	try{
	con = DriverManager.getConnection(url);
	callStatement =  con.prepareCall("{call patientnotifs(?)}");
	callStatement.setInt("patient_id",patient_id);
	ResultSet notifs = callStatement.executeQuery();
	
	lstnotif = new ArrayList <PatientNotif> ();
	while (notifs.next())
	{
		notif = new PatientNotif();
		notif.appt_Date = notifs.getDate("appt_date");
		notif.app_Status = notifs.getString("app_status");
		notif.firstName  = notifs.getString("firstname");
		notif.lastName  = notifs.getString("lastname");
		lstnotif.add(notif);
	}
	}
	catch(Exception ex) {
	 ex.getMessage();
	}
	finally{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	return lstnotif;
	}

}


