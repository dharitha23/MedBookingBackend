package com.MediBook.DataLayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.MediBook.DataLayer.InterfacesDataLayer.IConfirmAppointmentDL;
import com.MediBook.Model.ConfirmAppointment;

// Sarmad Noor
//This layer will have database related operation for a feature for application.
@Repository
public class ConfirmAppointmentDL implements IConfirmAppointmentDL {
	@Autowired
	private Environment envr;

	public boolean InsertBookingDetail(ConfirmAppointment appointmentDetail) {

		Connection con = null;
		CallableStatement callstmnt = null;
		String url = envr.getProperty("connectionstring");
		boolean isBookingSuccessful = false;
		int rowCount = 0;
		try {
			con = DriverManager.getConnection(url);
			callstmnt = con.prepareCall("{call dbo.sp_insertbookingdetail(?,?,?,?,?,?,?)}");
			callstmnt.setString("app_status", appointmentDetail.appt_Status);
			callstmnt.setString("address", appointmentDetail.address);
			// callstmnt.setDate("appt_date",appointmentDetail.appt_Date);
			callstmnt.setDate("appt_date", appointmentDetail.appointmentDate);
			callstmnt.setString("slot_id", Integer.toString(appointmentDetail.slot_Id));
			callstmnt.setString("patient_id", Integer.toString(appointmentDetail.patient_Id));
			callstmnt.setString("doctor_id", Integer.toString(appointmentDetail.doctor_Id));
			callstmnt.setString("problemDesc", appointmentDetail.problemDesc);
			rowCount = callstmnt.executeUpdate();
			if (rowCount > 0)
				isBookingSuccessful = true;

		} catch (Exception ex) {
			rowCount = -1;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isBookingSuccessful;
	}
// https://examples.javacodegeeks.com/core-java/util/date/java-util-date-to-java-sql-date/
// private static java.sql.Date ConvertUtiltoSql (java.util.Date bookingDate)
// {	
// java.sql.Date sBookingDate = new java.sql.Date(bookingDate.getTime());
// return sBookingDate;
// }

	public List<ConfirmAppointment> GetDoctorAvailablity(Integer doctor_id, Date appointmentDate) {
		Connection con = null;
		CallableStatement callStatement = null;
		String url = envr.getProperty("connectionstring");
// Integer slotid = -1;
// String appt_Status ="NA";
// Date appt_date;
		ConfirmAppointment bookingdetail = null;
		List<ConfirmAppointment> lstBookingDetail = null;
		try {
			con = DriverManager.getConnection(url);
			callStatement = con.prepareCall("{call sp_getdoctoravailability(?,?)}");
//LocalDate bookingDate = LocalDate.now();
// callStatement.setDate("appt_date",Date.valueOf(bookingDate));
			callStatement.setDate("appt_date", appointmentDate);
			callStatement.setInt("doctor_id", doctor_id);
			ResultSet doctorAvailSlots = callStatement.executeQuery();

//ConfirmAppointment.DocAvailabilty obj = bookingdetail.new  DocAvailabilty();
			lstBookingDetail = new ArrayList<ConfirmAppointment>();
			while (doctorAvailSlots.next()) {
				bookingdetail = new ConfirmAppointment();
				bookingdetail.starting_Slot = bookingdetail.new DocAvailabilty();
				bookingdetail.slot_Id = doctorAvailSlots.getInt("slot_id");
				bookingdetail.appt_Status = doctorAvailSlots.getString("app_status");
				bookingdetail.starting_Slot.startTimeSlot = doctorAvailSlots.getString("start_time_slot");
				lstBookingDetail.add(bookingdetail);
			}
		} catch (Exception ex) {
			bookingdetail.slot_Id = -2;
			ex.getMessage();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lstBookingDetail;
	}

}
