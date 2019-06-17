package com.MediBook.ServiceLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MediBook.DataLayer.InterfacesDataLayer.DoctorSignupIDL;
import com.MediBook.Model.DoctorProfile;
import com.MediBook.Model.User;
import com.MediBook.ServiceLayer.InterfacesServicelayer.DoctorSignupISL;


@Service
public class DoctorSignupSL implements DoctorSignupISL {
	@Autowired
	DoctorSignupIDL doctorsignupDL;
	public void addDoctor(DoctorProfile doctor)
	{
		//System.out.println("in DoctorSignupSL addDoctor");
		doctorsignupDL.addDoctor(doctor);
		
	} 
	
	public List<User> getUsers(String userName) {
		return doctorsignupDL.getUsers(userName);
	}
	
	public void setDetails(DoctorProfile doctor) {
		doctorsignupDL.setDetails(doctor);
	}
	
	public DoctorProfile getDocDetails(String email) 
	{
		return doctorsignupDL.getDocDetails(email);
	}
	
	public List<User> getAllUsers(){
		return doctorsignupDL.getAllUsers();
	}
}