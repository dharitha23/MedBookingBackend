package com.MediBook.DataLayer.InterfacesDataLayer;


import java.util.List;

import com.MediBook.Model.DoctorProfile;
import com.MediBook.Model.User;

public interface DoctorSignupIDL {
	void addDoctor (DoctorProfile doctor);
	List<User> getUsers(String userName);
	void setDetails(DoctorProfile doctor);
	DoctorProfile getDocDetails(String email);
	List<User> getAllUsers();
}
