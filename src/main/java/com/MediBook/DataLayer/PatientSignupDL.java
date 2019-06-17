package com.MediBook.DataLayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.MediBook.DataLayer.InterfacesDataLayer.PatientSignupIDL;
import com.MediBook.Model.PatientForRating;
import com.MediBook.Model.PatientProfile;
import com.MediBook.Model.Rating;

import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class PatientSignupDL implements PatientSignupIDL  {
     
	@Autowired
	private Environment env;
	
	public void addPatient(PatientProfile patient) {
		//System.out.print("Trying to insert patient");
		Connection con = null;
		CallableStatement call_statement = null;
		String con_url = env.getProperty("connectionstring");
		String con_result = "Success";
		try {
			String md5_pwd = null;
			con = DriverManager.getConnection(con_url);
			call_statement = con.prepareCall("{call dbo.addpatient(?,?)}");
			call_statement.setString("emailaddr", patient.getEmail());
			String pwd = patient.getPassword();
			String salt = "Welcome#ToTheMedBooking#WebApplication!!!12@$@4&#%^$*";
			String pwd_with_salt = pwd + salt;
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(pwd_with_salt.getBytes(), 0, pwd_with_salt.length());
			md5_pwd = new BigInteger(1, digest.digest()).toString(16);
			System.out.print("Encrypted pwd: " +md5_pwd);
			call_statement.setString("pwd", md5_pwd);
			call_statement.execute();
			con_result = "connected";

		} catch (Exception e) {
			con_result = e.getMessage();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		
	}
	public List<PatientProfile> getPatients() {
		
		return null;
		
	}
	
	public PatientForRating getPatient(String email) {
		Connection conn = null;
		CallableStatement call_pstatement = null;
		String conn_url = env.getProperty("connectionstring");
		String conn_result = "Success";
		PatientForRating patient = null;
		try {
			conn = DriverManager.getConnection(conn_url);
			call_pstatement = conn.prepareCall("{call dbo.getpatdetailsbyemail(?)}");
			call_pstatement.setString("email",email); 
			ResultSet pat = call_pstatement.executeQuery();
			while(pat.next()) {
			// System.out.print("pat object length: " +pat.getFetchSize());
			patient = new PatientForRating();
			patient.id = pat.getInt("patient_id");
			patient.firstName = pat.getString("firstname");
			System.out.print("pat firstname is:  " +pat.getString("firstname"));
			patient.lastName = pat.getString("lastname");
			System.out.print("pat lastname is: " +pat.getString("lastname"));
			}
		}
		catch (Exception e) {
			conn_result = e.getMessage();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	} 
		
		return patient;
		
	}
	
	public void setRating(Rating rating) {
		Connection conn = null;
		CallableStatement call_pstatement = null;
		String conn_url = env.getProperty("connectionstring");
		String conn_result = "Success";
		PatientForRating patient = null;
		try {
			conn = DriverManager.getConnection(conn_url);
			call_pstatement = conn.prepareCall("{call dbo.setrating(?,?,?,?,?)}");
			System.out.print("Message is: " +rating.getMessage());
			call_pstatement.setString("message",rating.getMessage()); 
			call_pstatement.setInt("stars",rating.getStars()); 
			System.out.print("Stars received: "+rating.getStars());
			call_pstatement.setInt("doc_id",rating.getDoctor_id());
			System.out.print("doc id: " +rating.getDoctor_id());
			call_pstatement.setInt("pat_id",rating.getPatient_id()); 
			System.out.print("patient id : " +rating.getPatient_id());
			call_pstatement.setDate("rating_date",rating.getRating_date()); 

			call_pstatement.execute();
		}
		catch (Exception e) {
			conn_result = e.getMessage();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	} 
		
	} 
	
	public void setPatDetails(PatientProfile patient) {
		Connection conn = null;
		CallableStatement call_statement = null;
		String conn_url = env.getProperty("connectionstring");
		String conn_result = "Success";
		int rowcount =0;
		try {
			String md5_pwd = null;
			conn = DriverManager.getConnection(conn_url);
			if(patient.getPassword() != "")
			{
			System.out.print("in update with pwd");
			call_statement = conn.prepareCall("{call dbo.updatepatientdetails(?,?,?,?,?,?)}");
			call_statement.setString("email", patient.email);
			call_statement.setString("firstname", patient.firstName);
			call_statement.setString("lastname", patient.lastName);
			String pwd = patient.getPassword();
			String salt = "Welcome#ToTheMedBooking#WebApplication!!!12@$@4&#%^$*";
			String pwd_with_salt = pwd + salt;
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(pwd_with_salt.getBytes(), 0, pwd_with_salt.length());
			md5_pwd = new BigInteger(1, digest.digest()).toString(16);
			call_statement.setString("pwd", md5_pwd);
			call_statement.setString("mobile", patient.contactNumber);
			call_statement.setDate("birthday", patient.birthday);
			rowcount = call_statement.executeUpdate();
			conn_result = "connected";	
			}
			else {
				System.out.print("in update without pwd");
				call_statement = conn.prepareCall("{call dbo.updatepatientdetailswithoutpwd(?,?,?,?,?)}");
				call_statement.setString("email", patient.email);
				call_statement.setString("firstname", patient.firstName);
				call_statement.setString("lastname", patient.lastName);
				call_statement.setString("mobile", patient.contactNumber);
				call_statement.setDate("birthday", patient.birthday);
				rowcount = call_statement.executeUpdate();
				conn_result = "connected";	
			}
		}
		catch (Exception e) {
			conn_result = e.getMessage();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	} 
		
	}
	
	public PatientProfile getPatDetails(String email) {
		Connection conn = null;
		CallableStatement call_pstatement = null;
		String conn_url = env.getProperty("connectionstring");
		String conn_result = "Success";
		PatientProfile patient = null;
		// System.out.print("getPatDetails");
		try {
//			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			// System.out.print("In try");
			// System.out.print("User email: " +email);
			conn = DriverManager.getConnection(conn_url);
			call_pstatement = conn.prepareCall("{call dbo.getpatdetailsbyemail(?)}");
			call_pstatement.setString("email",email); 
			ResultSet pat = call_pstatement.executeQuery();
			while(pat.next()) {
			// System.out.print("pat object length: " +pat.getFetchSize());
			patient = new PatientProfile();
			patient.patientID = pat.getInt("patient_id");
			patient.type = "patient";
			patient.setContactNumber(pat.getString("phone_number"));
			System.out.print("Phone number is:" +pat.getString("phone_number"));
			patient.email = pat.getString("email");
			System.out.print("Email is:  " +pat.getString("email"));
			patient.setFirstName(pat.getString("firstname"));
			System.out.print("pat firstname is:  " +pat.getString("firstname"));
			patient.password = pat.getString("password");
			System.out.print("Password is:  " +pat.getString("password"));
			patient.setLastName(pat.getString("lastname"));
			System.out.print("pat lastname is: " +pat.getString("lastname"));
			Date date = pat.getDate("birth_date");
			System.out.print("Birthday is:  " +pat.getDate("birth_date"));
			patient.setBirthday(date);
			}
		}
		catch (Exception e) {
			conn_result = e.getMessage();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	} 
		
		return patient;
		
	}
}
