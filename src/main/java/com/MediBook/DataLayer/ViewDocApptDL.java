package com.MediBook.DataLayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.MediBook.DataLayer.InterfacesDataLayer.IViewDocApptDL;
import com.MediBook.Model.ViewDoctorAppointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository
public class ViewDocApptDL implements IViewDocApptDL {
	@Autowired
	private Environment envr;

	public List<ViewDoctorAppointment> getAppointmentsUsingDoctorID(Integer doctor_id) {

		Connection con = null;
		CallableStatement callStatement = null;
		String url = envr.getProperty("connectionstring");

		ViewDoctorAppointment doctorAppointment = null;
		List<ViewDoctorAppointment> lstdoctorAppointment = null;
		try {
			con = DriverManager.getConnection(url);
			callStatement = con.prepareCall("{call sp_getdoctorappointment(?)}");
			callStatement.setInt("doctor_id", doctor_id);
			ResultSet doctorAppointmentSet = callStatement.executeQuery();

			lstdoctorAppointment = new ArrayList<ViewDoctorAppointment>();
			while (doctorAppointmentSet.next()) {
				doctorAppointment = new ViewDoctorAppointment();
				doctorAppointment.appt_id = doctorAppointmentSet.getInt("appt_id");
				doctorAppointment.patientID = doctorAppointmentSet.getInt("patient_id");
				doctorAppointment.patientEmail = doctorAppointmentSet.getString("email");
				doctorAppointment.appt_status = doctorAppointmentSet.getString("app_status");
				doctorAppointment.patientName = doctorAppointmentSet.getString("firstname");
				doctorAppointment.patientLastName = doctorAppointmentSet.getString("lastname");
				doctorAppointment.problemDesc = doctorAppointmentSet.getString("problemdesc");
				doctorAppointment.startTimeSlot = doctorAppointmentSet.getString("start_time_slot");
				doctorAppointment.apptDate = doctorAppointmentSet.getDate("APPT_DATE");
				doctorAppointment.Appt_id = doctorAppointmentSet.getInt("appt_id");
				lstdoctorAppointment.add(doctorAppointment);
			}
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lstdoctorAppointment;

	}

	public Boolean apptConfirmationByDoc(Integer appt_Id) {
		Connection con = null;
		CallableStatement callStmnt = null;
		String url = envr.getProperty("connectionstring");
		int updateStatus = 0;
		boolean statusUpdated = false;
		try {
			con = DriverManager.getConnection(url);
			callStmnt = con.prepareCall("{call sp_updateappointmentStatus(?)}");
			callStmnt.setInt("appt_Id", appt_Id);
			updateStatus = callStmnt.executeUpdate();
			if (updateStatus > 0)
				statusUpdated = true;
			else
				statusUpdated = false;
		} catch (Exception ex) {
			updateStatus = -1;
			statusUpdated = false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return statusUpdated;
	}
}