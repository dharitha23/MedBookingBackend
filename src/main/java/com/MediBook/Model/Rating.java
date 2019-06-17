package com.MediBook.Model;

import java.sql.Date;

public class Rating {
	private int id;
	private int stars;
	private String message;
	private int patient_id;
	private int doctor_id;
	private String patient_firstname;
	private String patient_lastname;
	private Date rating_date;

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
	 * @return the stars
	 */
	public int getStars() {
		return stars;
	}

	/**
	 * @param stars the stars to set
	 */
	public void setStars(int stars) {
		this.stars = stars;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the patient_id
	 */
	public int getPatient_id() {
		return patient_id;
	}

	/**
	 * @param patient_id the patient_id to set
	 */
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	/**
	 * @return the doctor_id
	 */
	public int getDoctor_id() {
		return doctor_id;
	}

	/**
	 * @param doctor_id the doctor_id to set
	 */
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	/**
	 * @return the patient_firstname
	 */
	public String getPatient_firstname() {
		return patient_firstname;
	}

	/**
	 * @param patient_firstname the patient_firstname to set
	 */
	public void setPatient_firstname(String patient_firstname) {
		this.patient_firstname = patient_firstname;
	}

	/**
	 * @return the patient_lastname
	 */
	public String getPatient_lastname() {
		return patient_lastname;
	}

	/**
	 * @param patient_lastname the patient_lastname to set
	 */
	public void setPatient_lastname(String patient_lastname) {
		this.patient_lastname = patient_lastname;
	}

	/**
	 * @return the rating_date
	 */
	public Date getRating_date() {
		return rating_date;
	}

	/**
	 * @param rating_date the rating_date to set
	 */
	public void setRating_date(Date rating_date) {
		this.rating_date = rating_date;
	}

	/**
	 * This method is used to decide if the doctor will be featured in the ratings
	 * results
	 * 
	 * @return boolean
	 */
	public Boolean anyUnset() {
		if (this.stars == 0)
			return true;
		if (this.message == null || this.message.isEmpty())
			return true;
		if (this.patient_firstname == null || this.patient_firstname.isEmpty())
			return true;
		if (this.patient_lastname == null || this.patient_lastname.isEmpty())
			return true;
//		if (this.rating_date == null)
//			return true;
		return false;
	}

}
