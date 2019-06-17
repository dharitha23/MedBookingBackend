package com.MediBook.ServiceLayer.InterfacesServicelayer;

import java.sql.Date;
import java.util.List;

import com.MediBook.Model.DoctorProfile;
import com.MediBook.Model.User;

public interface DoctorSignupISL {
	void addDoctor (DoctorProfile doctor);
	List<User> getUsers(String userName);
	void setDetails (DoctorProfile doctor);
	DoctorProfile getDocDetails (String email);
	List<User> getAllUsers();
}
