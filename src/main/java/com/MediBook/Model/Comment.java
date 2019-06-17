package com.MediBook.Model;

import java.sql.Date;

public class Comment {
	private int id;
	private int appt_id;
	private String comm_message;
	private Date appt_date;
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
	 * @return the appt_id
	 */
	public int getAppt_id() {
		return appt_id;
	}
	/**
	 * @param appt_id the appt_id to set
	 */
	public void setAppt_id(int appt_id) {
		this.appt_id = appt_id;
	}
	/**
	 * @return the comm_message
	 */
	public String getComm_message() {
		return comm_message;
	}
	/**
	 * @param comm_message the comm_message to set
	 */
	public void setComm_message(String comm_message) {
		this.comm_message = comm_message;
	}
	/**
	 * @return the appt_date
	 */
	public Date getAppt_date() {
		return appt_date;
	}
	/**
	 * @param appt_date the appt_date to set
	 */
	public void setAppt_date(Date appt_date) {
		this.appt_date = appt_date;
	}

}
