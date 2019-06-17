package com.MediBook.Model;

import java.util.ArrayList;

//This layer will have all the model for application.
public class Doctor {
	private int id;
	private String type;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone_number;
	private String speciality;
	private String address;
	private String latitude;
	private String longitude;
	private String profile_pic_path;
	private int total_ratings;
	private int stars_avg;
	private ArrayList<Rating> rating_reviews;

	public Doctor() {
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the phone_number
	 */
	public String getPhone_number() {
		return phone_number;
	}

	/**
	 * @param phone_number the phone_number to set
	 */
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	/**
	 * @return the specialty
	 */

	public String getSpeciality() {
		return speciality;
	}

	/**
	 * @param speciality the specialty to set
	 */
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getProfile_pic_path() {
		return profile_pic_path;
	}

	public void setProfile_pic_path(String profile_pic_path) {
		this.profile_pic_path = profile_pic_path;
	}

	/**
	 * @return the total_ratings
	 */
	public int getTotal_ratings() {
		return total_ratings;
	}

	/**
	 * @param total_ratings the total_ratings to set
	 */
	public void setTotal_ratings(int total_ratings) {
		this.total_ratings = total_ratings;
	}

	/**
	 * @return the stars_avg
	 */
	public int getStars_avg() {
		return stars_avg;
	}

	/**
	 * @param stars_avg the stars_avg to set
	 */
	public void setStars_avg(int stars_avg) {
		this.stars_avg = stars_avg;
	}

	/**
	 * @return the rating_reviews
	 */
	public ArrayList<Rating> getRating_reviews() {
		return rating_reviews;
	}

	/**
	 * @param rating_reviews the rating_reviews to set
	 */
	public void setRating_reviews(ArrayList<Rating> rating_reviews) {
		this.rating_reviews = rating_reviews;
	}
	

	/**

	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Originally taken from
	 * https://stackoverflow.com/questions/12362212/what-is-the-best-way-to-know-if-all-the-variables-in-a-class-are-null/40238927
	 * but then changed to check if the retrieved values where empty or not.
	 * 
	 * This method is used to decide if the doctor will be featured in the search
	 * results
	 * 
	 * @return boolean
	 */
	public Boolean anyUnset() {
		if (this.firstName == null || this.firstName.isEmpty())
			return true;
		if (this.lastName == null || this.lastName.isEmpty())
			return true;
		if (this.email == null || this.email.isEmpty())
			return true;
		if (this.phone_number == null || this.phone_number.isEmpty())
			return true;
		if (this.speciality == null || this.speciality.isEmpty())
			return true;
		if (this.address == null || this.address.isEmpty())
			return true;
		if (this.latitude == null || this.latitude.isEmpty())
			return true;
		if (this.longitude == null || this.longitude.isEmpty())
			return true;
		return false;
	}
}
