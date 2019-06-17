package com.MediBook.Model;

import java.util.Date;

public class PatientProfile {

	public int patientID;
	public String type;
	public String firstName;
	public String lastName;
	public String email;
	public String password;
	public java.sql.Date birthday;
	public String contactNumber;

//		public int getPatientID() {
//			return patientID;
//		}
//		public void setPatientID(int patientID) {
//			this.patientID = patientID;
//		}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
