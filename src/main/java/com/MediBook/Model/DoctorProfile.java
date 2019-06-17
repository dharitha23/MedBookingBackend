package com.MediBook.Model;

public class DoctorProfile {
	
		public int docID;
		public String type;
		public String firstName;
		public String lastName;
		public String email;
		public String password;
		public String speciality;
		public String contactNumber;
		public String address;
		public String latitude;
		public String longitude;
		public int getDocID() {
			return docID;
		}
		public void setDocID(int docID) {
			this.docID = docID;
		}
		public DoctorProfile(String type, String firstName, String lastName, String email, String password, String speciality,
				String contactNumber, String address, String latitude, String longitude) {
			super();
			this.type = type;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
			this.speciality = speciality;
			this.contactNumber = contactNumber;
			this.address = address;
			this.latitude = latitude;
			this.longitude = longitude;
		}
		public DoctorProfile() {
			super();
			// TODO Auto-generated constructor stub
		}
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
		public String getSpeciality() {
			return speciality;
		}
		public void setSpeciality(String speciality) {
			this.speciality = speciality;
		}
		public String getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

}
