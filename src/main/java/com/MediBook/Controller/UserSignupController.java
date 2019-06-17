package com.MediBook.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpSession;
import com.MediBook.ServiceLayer.InterfacesServicelayer.DoctorSignupISL;

import com.MediBook.Model.DoctorProfile;
import com.MediBook.Model.NewUser;
import com.MediBook.Model.PatientForRating;
import com.MediBook.Model.PatientProfile;
import com.MediBook.Model.Rating;
import com.MediBook.Model.User;
import com.MediBook.Model.UserSession;
import com.MediBook.ServiceLayer.InterfacesServicelayer.PatientSignupISL;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Scope(value="session")
public class UserSignupController  {	
	
List<UserSession> sessionarray = new ArrayList<UserSession>();

@Autowired
DoctorSignupISL doctorsignupISL;

@Autowired
PatientSignupISL patientsignupISL;
	
// fetch users
	@RequestMapping(value="/signupin", method = RequestMethod.GET)
	public List<User> fetchUsers(@RequestParam("userName") String userName)	{
		return this.doctorsignupISL.getUsers(userName);
	}
	
	//fetching patient details for rating 
	@RequestMapping(value="/fetchPatient", method = RequestMethod.GET)
	public PatientForRating fetchPatient(@RequestParam("email") String email)	{
		return this.patientsignupISL.getPatient(email);
	}
	
	@RequestMapping(value="/rating", method = RequestMethod.POST)
	public void insertRating(@RequestBody Rating rating)	{
		System.out.print("inside insert rating");
		patientsignupISL.setRating(rating);
	}
		
	
	// add a new user
	@RequestMapping(value = "/signupin", method = RequestMethod.POST)
	public void insertNewUser(@RequestBody NewUser user) {
		//System.out.println("insert newuser method");
		
		if(user.type.equalsIgnoreCase("doctor")) {
			//System.out.println(user.type);
			DoctorProfile doctor = new DoctorProfile();
			//doctor.setDocID(user.Id);
			doctor.setEmail(user.email);
			doctor.setPassword(user.password);
			doctor.setType(user.type);
			doctorsignupISL.addDoctor(doctor);
		}
		else {
			//System.out.print("Entering else");
			PatientProfile patient = new PatientProfile();
			//patient.setPatientID(user.Id);
			patient.setEmail(user.email);
			patient.setPassword(user.password);
			patient.setType(user.type);
			patientsignupISL.addPatient(patient);
		}
	}
	
	// create a session
//	@RequestMapping(value = "/bookappointment",method = RequestMethod.POST)
//	public void createUserSession(@RequestBody User user,HttpSession session) {
//		UserSession usersession  = new UserSession();		
//		usersession.setUser(user);
//		session.setAttribute("user", usersession.getId());
//		System.out.print("Email is " +usersession.getUser().email);
//		System.out.print("Password is " +usersession.getUser().password);
////		sessionarray.add(hello);
////		System.out.print("HI");
////		System.out.print("Session created" +hello.getId());
////		//return hello.getId();
//	}
	
	@RequestMapping(value="/docprofile", method = RequestMethod.POST)
	public void setDetails(@RequestBody DoctorProfile doc)	{
		this.doctorsignupISL.setDetails(doc);
	}
	
	@RequestMapping(value="/myprofile", method = RequestMethod.POST)
	public void setPatDetails(@RequestBody PatientProfile pat)	{
		this.patientsignupISL.setPatDetails(pat);
	}
	
	
	@RequestMapping(value="/docprofile", method = RequestMethod.GET)
	public DoctorProfile getDocDetails(@RequestParam("email") String email)	{
		return this.doctorsignupISL.getDocDetails(email);
	}
	
	@RequestMapping(value="/myprofile", method = RequestMethod.GET)
	public PatientProfile getPatDetails(@RequestParam("email") String email)	
	{
		return this.patientsignupISL.getPatDetails(email);
	}
	
	
	}