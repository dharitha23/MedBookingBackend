
package com.MediBook.Model;

import java.sql.Date;
import java.sql.Time;

public class AppointmentsForPatient {

	public int patient_id;
	public int doctor_id;
	public int appointment_id;
	public String firstname;
	public String lastname;
	public Date appt_date;
	public String problemdesc;
	public String appt_time;
	public String appt_status;

	//Getters and setters for the model
	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public int getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}

}

