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

import com.MediBook.DataLayer.InterfacesDataLayer.IDocNotifDL;
import com.MediBook.Model.DocNotif;

@Repository
public class DocNotifDL implements IDocNotifDL {
	@Autowired
	private Environment envr;
	
	public List<DocNotif> GetDoctorNotif(Integer doctor_id)
	{
	Connection con = null;
	CallableStatement callStatement = null;
	String url = envr.getProperty("connectionstring");
	DocNotif notif = null; 
	List <DocNotif> lstnotif = null;
	try{
	con = DriverManager.getConnection(url);
	callStatement =  con.prepareCall("{call testdocnotifs(?)}");
	callStatement.setInt("doctor_id",doctor_id);
	ResultSet notifs = callStatement.executeQuery();
	
	lstnotif = new ArrayList <DocNotif> ();
	while (notifs.next())
	{
		notif = new DocNotif();
		notif.appt_Date = notifs.getDate("appt_date");
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
