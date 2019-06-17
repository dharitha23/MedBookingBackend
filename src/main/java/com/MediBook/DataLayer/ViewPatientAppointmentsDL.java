package com.MediBook.DataLayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.MediBook.DataLayer.InterfacesDataLayer.IPatientAppointments;
import com.MediBook.Model.AppointmentsForPatient;

@Repository
public class ViewPatientAppointmentsDL implements IPatientAppointments {

	@Autowired
	private Environment envr;

	// Method for returning all the available appointments from the database
	@Override
	public List<AppointmentsForPatient> getAppointmentsUsingPatientID(int patientID) {
		Connection connection = null;
		String connectionURL = envr.getProperty("connectionstring");
		List<AppointmentsForPatient> appointmentListForAPatient = new ArrayList<AppointmentsForPatient>();

		try {
			connection = DriverManager.getConnection(connectionURL);
			Statement stmt = connection.createStatement();
			String sqlQuery = "SELECT \r\n" + "    firstname,\r\n" + "    patient_appts.patient_id, doctor.doctor_id,\r\n"
					+ "    lastname,\r\n" + "    appt_date, app_status,\r\n" + "    problemdesc, \r\n"
					+ "    patient_appts.appt_id, \r\n" + "    doc_availtime_slot.start_time_slot \r\n" + "FROM\r\n"
					+ "    appointment \r\n" + "INNER JOIN\r\n"
					+ "    patient_appts ON patient_appts.appt_id = appointment.appt_id\r\n" + "INNER JOIN\r\n"
					+ "    doctor_appts ON patient_appts.appt_id = doctor_appts.appt_id\r\n" + "INNER JOIN\r\n"
					+ "    doctor ON doctor_appts.doctor_id = doctor.doctor_id\r\n" + "INNER JOIN\r\n"
					+ "    doc_availtime_slot ON doc_availtime_slot.slot_id = appointment.slot_id\r\n"
					+ "WHERE patient_appts.patient_id = " + patientID;

			ResultSet rs = stmt.executeQuery(sqlQuery);

			while (rs.next()) {
				AppointmentsForPatient appointmentDetails = new AppointmentsForPatient();
				appointmentDetails.problemdesc = rs.getString("PROBLEMDESC");
				appointmentDetails.patient_id = rs.getInt("PATIENT_ID");
				appointmentDetails.firstname = rs.getString("FIRSTNAME");
				appointmentDetails.lastname = rs.getString("LASTNAME");
				appointmentDetails.appt_status = rs.getString("app_status");
				appointmentDetails.appt_date = rs.getDate("APPT_DATE");
				appointmentDetails.appointment_id = rs.getInt("appt_id");
				appointmentDetails.doctor_id = rs.getInt("doctor_id");
				Time appttime = rs.getTime("start_time_slot");
				System.out.print("Time: " + appttime);
				String new_appt_time = appttime.toString();
				new_appt_time = new_appt_time.substring(0, 5);
				System.out.print("New Time: " + new_appt_time);
				System.out.print("Hour: " + new_appt_time.substring(0, 2));
				int hr = Integer.parseInt(new_appt_time.substring(0, 2));

				if (hr >= 12) {
					new_appt_time = new_appt_time.concat(" PM");
					System.out.print("Time PM: " + new_appt_time);
				} else {
					new_appt_time = new_appt_time.concat(" AM");
					System.out.print("Time AM: " + new_appt_time);
				}
				hr = hr % 12;
				String appt_hr = Integer.toString(hr);
				String appt_time = appt_hr + new_appt_time.substring(2, 8);
				System.out.print("12 hour time with AM/PM: " + appt_time);
				appointmentDetails.appt_time = appt_time;
				appointmentListForAPatient.add(appointmentDetails);
			}
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return appointmentListForAPatient;
	}

	// Method to delete an appointment with an appointment id
	public void deleteByAppointmentID(Integer appt_id) {
		Connection connection = null;
		String connectionURL = envr.getProperty("connectionstring");
		CallableStatement callStatement = null;
		int rowAffected = 0;

		try {
			connection = DriverManager.getConnection(connectionURL);
			callStatement = connection.prepareCall("{call deleteAppointment(?)}");
			callStatement.setInt("appt_id", appt_id);
			rowAffected = callStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
