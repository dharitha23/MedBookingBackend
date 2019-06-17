package com.MediBook.DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.MediBook.DataLayer.InterfacesDataLayer.IDoctorDL;
import com.MediBook.Model.Comment;
import com.MediBook.Model.Doctor;
import com.MediBook.Model.Patient;
import com.MediBook.Model.Rating;

@Repository
public class DoctorDL implements IDoctorDL {
	@Autowired
	private Environment env;

	@Override
	public Doctor getDoctorById(int id) {
		Doctor doctor = null;
		String url = env.getProperty("connectionstring").toString();
		Connection connection = null;
		ResultSet queryResult = null;

		try {
			connection = DriverManager.getConnection(url);

			PreparedStatement statement = connection.prepareStatement("SELECT * FROM dbo.doctor WHERE doctor_id = ?");
			statement.setInt(1, id);

			queryResult = statement.executeQuery();

			while (queryResult.next()) {
				doctor = new Doctor();
				doctor.setId(queryResult.getInt("doctor_id"));
				doctor.setFirstName(queryResult.getString("firstname"));
				doctor.setLastName(queryResult.getString("lastname"));
				doctor.setEmail(queryResult.getString("email"));
				doctor.setPhone_number(queryResult.getString("phone_number"));
				doctor.setSpeciality(queryResult.getString("specialty"));
				doctor.setAddress(queryResult.getString("address"));
				doctor.setLatitude(queryResult.getString("latitude"));
				doctor.setLongitude(queryResult.getString("longitude"));
				doctor.setRating_reviews(getDoctorRatingsById(doctor.getId()));
				doctor.setProfile_pic_path(queryResult.getString("profile_pic_path"));
				setDoctorTotalRatingsInfo(doctor, connection);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return doctor;
	}

	/**
	 * Retrieves doctor's ratings
	 * 
	 * @param id
	 * @return
	 */

	public ArrayList<Rating> getDoctorRatingsById(int id) {
		Rating rating = null;
		String url = env.getProperty("connectionstring").toString();
		Connection connection = null;
		ResultSet queryResult = null;
		ArrayList<Rating> ratings = new ArrayList<Rating>();

		try {
			connection = DriverManager.getConnection(url);

			PreparedStatement statement = connection.prepareStatement(
					"SELECT rating_id, stars,rating_message, dbo.patient.firstname as patient_firstname, dbo.patient.lastname as patient_lastname,\r\n"
							+ "		rating_date" + "		FROM dbo.ratings"
							+ "		INNER JOIN dbo.patient ON dbo.ratings.patient_id = dbo.patient.patient_id"
							+ "		WHERE doctor_id = ?");
			statement.setInt(1, id);

			queryResult = statement.executeQuery();

			while (queryResult.next()) {
				rating = new Rating();
				rating.setId(queryResult.getInt("rating_id"));
				rating.setStars(queryResult.getInt("stars"));
				rating.setMessage(queryResult.getString("rating_message"));
				rating.setPatient_firstname(queryResult.getString("patient_firstname"));
				rating.setPatient_lastname(queryResult.getString("patient_lastname"));
				rating.setRating_date(queryResult.getDate("rating_date"));

				if (!rating.anyUnset()) {
					ratings.add(rating);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ratings;
	}

	/**
	 * Retrieves doctor's ratings information from db to show in the search results.
	 * 
	 * @param doctor
	 * @param connection
	 */
	public void setDoctorTotalRatingsInfo(Doctor doctor, Connection connection) {
		try {
			PreparedStatement statement = connection.prepareStatement(
					"select count(*) as ratings_total, AVG(stars) as stars_avg FROM dbo.ratings WHERE doctor_id = ?;");
			statement.setInt(1, doctor.getId());

			ResultSet queryResult = statement.executeQuery();

			while (queryResult.next()) {
				doctor.setTotal_ratings(queryResult.getInt("ratings_total"));
				doctor.setStars_avg(queryResult.getInt("stars_avg"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Patient> getDoctorPatients(Integer id) {
		Patient patient = null;
		String url = env.getProperty("connectionstring").toString();
		Connection connection = null;
		ResultSet queryResult = null;
		ArrayList<Patient> patients = new ArrayList<Patient>();

		try {
			connection = DriverManager.getConnection(url);

			PreparedStatement statement = connection.prepareStatement(
					"SELECT firstname, lastname, patient.patient_id, email, birth_date, phone_number\r\n"
							+ "FROM patient\r\n" + "JOIN (SELECT DISTINCT patient_appts.patient_id, doctor_id\r\n"
							+ "FROM patient_appts\r\n" + "JOIN doctor_appts\r\n"
							+ "ON patient_appts.appt_id = doctor_appts.appt_id) as results\r\n"
							+ "ON patient.patient_id = results.patient_id where results.doctor_id = ?");

			statement.setInt(1, id);

			queryResult = statement.executeQuery();

			while (queryResult.next()) {
				patient = new Patient();
				patient.setPatientID(queryResult.getInt("patient_id"));
				patient.setFirstName(queryResult.getString("firstname"));
				patient.setLastName(queryResult.getString("lastname"));
				patient.setEmail(queryResult.getString("email"));
				patient.setContactNumber(queryResult.getString("phone_number"));
				patient.setBirthday(queryResult.getDate("birth_date"));

				if (!patient.anyUnsetForPatientsList()) {
					patients.add(patient);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return patients;
	}

	@Override
	public Object saveAppointmentNotes(Comment comment) {
		String url = env.getProperty("connectionstring").toString();
		Connection connection = null;
		ResultSet queryResult = null;

		try {
			connection = DriverManager.getConnection(url);

			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO comment (comm_message, appt_id) " + "VALUES (?, ?)");

			statement.setString(1, comment.getComm_message());
			statement.setInt(2, comment.getAppt_id());
			queryResult = statement.executeQuery();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return queryResult;
	}

	@Override
	public ArrayList<Comment> getPatientComments(Integer id) {
		Comment comment = null;
		String url = env.getProperty("connectionstring").toString();
		Connection connection = null;
		ResultSet queryResult = null;
		ArrayList<Comment> comments = new ArrayList<Comment>();

		try {
			connection = DriverManager.getConnection(url);

			PreparedStatement statement = connection.prepareStatement(
					"select appt_date,comm_message, comment_id, patient_appts.appt_id from patient_appts "
							+ "join appointment on patient_appts.appt_id = appointment.appt_id "
							+ "join comment on patient_appts.appt_id = comment.appt_id " + "where patient_id = ?");
			statement.setInt(1, id);

			queryResult = statement.executeQuery();

			while (queryResult.next()) {
				comment = new Comment();
				comment.setId(queryResult.getInt("comment_id"));
				comment.setAppt_id(queryResult.getInt("appt_id"));
				comment.setComm_message(queryResult.getString("comm_message"));
				comment.setAppt_date(queryResult.getDate("appt_date"));

				comments.add(comment);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return comments;
	}

}
