package com.MediBook.DataLayer;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.MediBook.DataLayer.InterfacesDataLayer.DoctorSignupIDL;
import com.MediBook.Model.DoctorProfile;

import com.MediBook.Model.User;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class DoctorSignupDL implements DoctorSignupIDL {
     
	@Autowired
	public Environment env;
	
	public void addDoctor(DoctorProfile doctor) {
		//System.out.println("trying to insert");
		
		Connection con = null;
		CallableStatement call_statement = null;
		String con_url = env.getProperty("connectionstring");
		String con_result = "Success";
		try {
			String md5_pwd = null;
			con = DriverManager.getConnection(con_url);
			call_statement = con.prepareCall("{call dbo.adddoctor(?,?)}");
			//call_statement.setString("id", Integer.toString(doctor.getDocID()));
			call_statement.setString("emailaddr", doctor.getEmail());
			//call_statement.setString("pwd", doctor.getPassword());
			String pwd = doctor.getPassword();
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
	public List<User> getUsers(String userName) {
			System.out.println("in dl get userssssssssssssssssssssss");
		
		Connection conn = null;
		CallableStatement call_dstatement = null;
		CallableStatement call_pstatement = null;
		String conn_url = env.getProperty("connectionstring");
		String conn_result = "Success";
		List<User> users = null ;
		User validUser;
		try {
			users = new ArrayList<User>();
			conn = DriverManager.getConnection(conn_url);
			call_dstatement = conn.prepareCall("{call dbo.getdoctorsbyname(?)}");
			call_dstatement.setString("userName",userName);
			ResultSet doc_list = call_dstatement.executeQuery();
			call_pstatement = conn.prepareCall("{call dbo.getpatientsbyname(?)}");
			call_pstatement.setString("userName",userName);
			ResultSet pat_list = call_pstatement.executeQuery();
				System.out.println("users length: " +users.size());
				if(doc_list != null) {
			while(doc_list.next())
			{
						System.out.println("doc list is not null....");
				System.out.print("Hi");
				validUser = new User();
				validUser.userID = doc_list.getInt("doctor_id");
				validUser.email = doc_list.getString("email");
				validUser.password= doc_list.getString("password");
				validUser.type = "doctor";
				System.out.print(validUser.type);
				System.out.print(validUser.email);
				System.out.print(validUser.userID);
				users.add(validUser);
			}
				}
				else {
					System.out.println("Empty doc list....");
				}
				if(pat_list != null) {
					System.out.println("pat list is not null....");
			while (pat_list.next()) {
				
				System.out.print("Hi");
				validUser = new User();
				validUser.userID = pat_list.getInt("patient_id");
				validUser.email = pat_list.getString("email");
				validUser.password= pat_list.getString("password");
				validUser.type = "patient";
				System.out.print(validUser.type);
				System.out.print(validUser.email);
				System.out.print(validUser.userID);
				users.add(validUser);
				//System.out.println("users length: " +users.size());
			}
				}
				else {
					System.out.println("Empty patient list....");
			}
			
			
			//con_result = "connected";

		} catch (Exception e) {
			conn_result = e.getMessage();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		return users;
		
	}
	
	public List<User> getAllUsers() {
		System.out.println("in dl get userssssssssssssssssssssss");
	
	Connection conn = null;
	CallableStatement call_dstatement = null;
	CallableStatement call_pstatement = null;
	String conn_url = env.getProperty("connectionstring");
	String conn_result = "Success";
	List<User> users = null ;
	User validUser;
	try {
		users = new ArrayList<User>();
		conn = DriverManager.getConnection(conn_url);
		call_dstatement = conn.prepareCall("{call dbo.getdoctors()}");
		ResultSet doc_list = call_dstatement.executeQuery();
		call_pstatement = conn.prepareCall("{call dbo.getpatients()}");
		ResultSet pat_list = call_pstatement.executeQuery();
			System.out.println("users length: " +users.size());
			if(doc_list != null) {
		while(doc_list.next())
		{
					System.out.println("doc list is not null....");
			System.out.print("Hi");
			validUser = new User();
			validUser.userID = doc_list.getInt("doctor_id");
			validUser.email = doc_list.getString("email");
			validUser.password= doc_list.getString("password");
			validUser.type = "doctor";
			System.out.print(validUser.type);
			System.out.print(validUser.email);
			System.out.print(validUser.userID);
			users.add(validUser);
		}
			}
			else {
				System.out.println("Empty doc list....");
			}
			if(pat_list != null) {
				System.out.println("pat list is not null....");
		while (pat_list.next()) {
			
			System.out.print("Hi");
			validUser = new User();
			validUser.userID = pat_list.getInt("patient_id");
			validUser.email = pat_list.getString("email");
			validUser.password= pat_list.getString("password");
			validUser.type = "patient";
			System.out.print(validUser.type);
			System.out.print(validUser.email);
			System.out.print(validUser.userID);
			users.add(validUser);
			//System.out.println("users length: " +users.size());
		}
			}
			else {
				System.out.println("Empty patient list....");
		}
		
		
		//con_result = "connected";

	} catch (Exception e) {
		conn_result = e.getMessage();
	} finally {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
	return users;
	
}
	
	public void setDetails(DoctorProfile doc) {
		Connection conn = null;
		CallableStatement call_statement = null;
		String conn_url = env.getProperty("connectionstring");
		String conn_result = "Success";
		
		try {
			String md5_pwd = null;
			conn = DriverManager.getConnection(conn_url);
			System.out.print("New password value: "+doc.getPassword());
			if(doc.getPassword() != "")
			{
			System.out.print("inside if");	
			call_statement = conn.prepareCall("{call dbo.updatedoctordetails(?,?,?,?,?,?,?,?,?)}");
			call_statement.setString("email", doc.getEmail());
			call_statement.setString("firstname", doc.getFirstName());
			call_statement.setString("lastname", doc.getLastName());
			String pwd = doc.getPassword();
			String salt = "Welcome#ToTheMedBooking#WebApplication!!!12@$@4&#%^$*";
			String pwd_with_salt = pwd + salt;
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(pwd_with_salt.getBytes(), 0, pwd_with_salt.length());
			md5_pwd = new BigInteger(1, digest.digest()).toString(16);
			call_statement.setString("pwd", md5_pwd);
			call_statement.setString("mobile", doc.getContactNumber());
			call_statement.setString("speciality", doc.getSpeciality());
			call_statement.setString("address", doc.getAddress());
			call_statement.setString("lat", doc.getLatitude());
			call_statement.setString("long", doc.getLongitude());
			call_statement.execute();
			conn_result = "connected";
			}
			else 
			{
				System.out.print("inside else");	
				call_statement = conn.prepareCall("{call dbo.updatedoctordetailswithoutpwd(?,?,?,?,?,?,?,?)}");
				call_statement.setString("email", doc.getEmail());
				call_statement.setString("firstname", doc.getFirstName());
				call_statement.setString("lastname", doc.getLastName());
				call_statement.setString("mobile", doc.getContactNumber());
				call_statement.setString("speciality", doc.getSpeciality());
				call_statement.setString("address", doc.getAddress());
				call_statement.setString("lat", doc.getLatitude());
				call_statement.setString("long", doc.getLongitude());
				call_statement.execute();
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
	
	public DoctorProfile getDocDetails(String email) {
		Connection conn = null;
		CallableStatement call_dstatement = null;
		CallableStatement call_pstatement = null;
		String conn_url = env.getProperty("connectionstring");
		String conn_result = "Success";
		DoctorProfile doctor = null;
		System.out.print("getDocDetails");
		try {
			System.out.print("In try");
			System.out.print("User email: " +email);
			conn = DriverManager.getConnection(conn_url);
			call_dstatement = conn.prepareCall("{call dbo.getdocdetailsbyemail(?)}");
			call_dstatement.setString("email",email);
			ResultSet doc = call_dstatement.executeQuery();
			while(doc.next()) {
			System.out.print("doc object lenght: " +doc.getFetchSize());
			doctor = new DoctorProfile();
			doctor.type = "doctor";
			doctor.docID =  doc.getInt("doctor_id");
			doctor.address = doc.getString("address");
			doctor.setContactNumber(doc.getString("phone_number"));
			doctor.email = doc.getString("email");
			doctor.setFirstName(doc.getString("firstname"));
			System.out.print("doc fname: " +doc.getString("firstname"));
			doctor.setLastName(doc.getString("lastname"));
			doctor.setLatitude(doc.getString("latitude"));
			doctor.setLongitude(doc.getString("longitude"));
			doctor.setPassword(doc.getString("password"));
			doctor.setSpeciality(doc.getString("specialty"));
			System.out.print(doc.getString("address"));
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
		
		return doctor;
		
	}
//	public int getUserIDbyEmail() {
//		
//		
//	}
}
