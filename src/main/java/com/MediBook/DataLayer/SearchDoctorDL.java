package com.MediBook.DataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.MediBook.DataLayer.InterfacesDataLayer.ISearchDoctorDL;
import com.MediBook.Model.Doctor;
import com.MediBook.Model.SearchParams;

import utils.Geolocation;

//This layer will have database related operation
@Repository
public class SearchDoctorDL implements ISearchDoctorDL {
	@Autowired
	private Environment env;

	@Override
	public Doctor[] SearchDoctorDL() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method to retrieve doctor records from database according to the received
	 * searchParams
	 */

	@Override
	public ArrayList<Doctor> searchDoctors(SearchParams searchParams) {
		System.out.print("\n SearchParams- " + searchParams.toString());
		String latitude = searchParams.getLatitude();
		String longitude = searchParams.getLongitude();
		String speciality = searchParams.getSpeciality();
		String searchText = searchParams.getSearchText();
		double earthRadius = 6371.01;// km
		double distance = 280; // Around 5km

		ArrayList<Doctor> searchResult = new ArrayList<Doctor>();
		String url = env.getProperty("connectionstring").toString();
		Connection connection = null;
		ResultSet queryResult = null;
		try {
			connection = DriverManager.getConnection(url);

			if (latitude.contentEquals("undefined") && longitude.contentEquals("undefined")
					&& speciality.isEmpty() & searchText.isEmpty()) {
				// Returns empty result
			} else {
				// Case: Only location is provided
				if (!latitude.contentEquals("undefined") && !longitude.contentEquals("undefined")
						&& speciality.isEmpty() & searchText.isEmpty()) {
					Geolocation myLocation = Geolocation.fromDegrees(Double.parseDouble(latitude),
							Double.parseDouble(longitude));
					queryResult = this.findDoctorsWithinDistance(earthRadius, myLocation, distance, connection);
				} else {
					// Case: location and speciality are provided
					if (!latitude.contentEquals("undefined") && !longitude.contentEquals("undefined")
							&& !speciality.isEmpty() & searchText.isEmpty()) {
						Geolocation myLocation = Geolocation.fromDegrees(Double.parseDouble(latitude),
								Double.parseDouble(longitude));
						if (speciality.contentEquals("any")) {
							queryResult = this.findDoctorsWithinDistance(earthRadius, myLocation, distance, connection);
						} else {
							queryResult = this.findDoctorsWithinDistanceAndSpeciality(earthRadius, myLocation, distance,
									speciality, connection);
						}
					} else {
						// Case: All location, speciality and search text are provided
						if (!latitude.contentEquals("undefined") && !longitude.contentEquals("undefined")
								&& !speciality.isEmpty() & !searchText.isEmpty()) {

							Geolocation myLocation = Geolocation.fromDegrees(Double.parseDouble(latitude),
									Double.parseDouble(longitude));
							queryResult = this.findDoctorsWithinDistanceAndSpecialityAndText(earthRadius, myLocation,
									distance, speciality, searchText, connection);
						} else {
							// Case: Just speciality
							if (latitude.contentEquals("undefined") && longitude.contentEquals("undefined")
									&& !speciality.isEmpty() & searchText.isEmpty()) {
								queryResult = this.findDoctorsBySpeciality(speciality, connection);
							} else {
								// Case: Speciality and text
								if (latitude.contentEquals("undefined") && longitude.contentEquals("undefined")
										&& !speciality.isEmpty() & !searchText.isEmpty()) {
									queryResult = this.findDoctorsBySpecialityAndText(speciality, searchText,
											connection);
								} else {
									// Case: Just text
									if (latitude.contentEquals("undefined") && longitude.contentEquals("undefined")
											&& speciality.isEmpty() & !searchText.isEmpty()) {
										queryResult = this.findDoctorsBySearchText(searchText, connection);
									}
								}
							}
						}
					}

				}

				// Iterates through the result and adds each doctor record to the searchResult
				// array.
				while (queryResult.next()) {
					Doctor doctor = new Doctor();
					doctor.setId(queryResult.getInt("doctor_id"));
					doctor.setFirstName(queryResult.getString("firstname"));
					doctor.setLastName(queryResult.getString("lastname"));
					doctor.setEmail(queryResult.getString("email"));
					doctor.setPhone_number(queryResult.getString("phone_number"));
					doctor.setSpeciality(queryResult.getString("specialty"));
					doctor.setAddress(queryResult.getString("address"));
					doctor.setLatitude(queryResult.getString("latitude"));
					doctor.setLongitude(queryResult.getString("longitude"));
					doctor.setProfile_pic_path(queryResult.getString("profile_pic_path"));
					setDoctorTotalRatingsInfo(doctor, connection);

					// TODO: retrieve other information such as ratings

					// This validation might just stay for now for A4 purpose.
					if (!doctor.anyUnset()) {
						searchResult.add(doctor);
					}
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

		return searchResult;
	}

	public ResultSet findDoctorsBySpeciality(String speciality, Connection connection) throws java.sql.SQLException {
		PreparedStatement statement = null;
		if (speciality.contentEquals("any")) {
			statement = connection.prepareStatement("SELECT * FROM dbo.doctor");
		} else {
			statement = connection.prepareStatement("SELECT * FROM dbo.doctor WHERE specialty = ?");
			statement.setString(1, speciality);
		}

		return statement.executeQuery();
	}

	public ResultSet findDoctorsBySpecialityAndText(String speciality, String text, Connection connection)
			throws java.sql.SQLException {
		PreparedStatement statement = null;
		if (speciality.contentEquals("any")) {
			statement = connection.prepareStatement(
					"SELECT * FROM dbo.doctor  WHERE (Contains(firstname, ?) OR CHARINDEX(firstname, ?, 1)<>0  OR CHARINDEX(lastname, ?, 1)<>0) ");
			statement.setString(1, "\"*" + text + "*\"");
			statement.setString(2, text);
			statement.setString(3, text);
		} else {
			statement = connection.prepareStatement(
					"SELECT * FROM dbo.doctor WHERE specialty = ? AND (Contains(firstname, ?) OR CHARINDEX(firstname, ?, 1)<>0  OR CHARINDEX(lastname, ?, 1)<>0)");
			statement.setString(1, speciality);
			statement.setString(2, "\"*" + text + "*\"");
			statement.setString(3, text);
			statement.setString(4, text);
		}

		return statement.executeQuery();
	}

	public ResultSet findDoctorsBySearchText(String text, Connection connection) throws java.sql.SQLException {
		PreparedStatement statement = null;
		statement = connection.prepareStatement(
				"SELECT * FROM dbo.doctor  WHERE (Contains(firstname, ?) OR CHARINDEX(firstname, ?, 1)<>0  OR CHARINDEX(lastname, ?, 1)<>0) ");
		statement.setString(1, "\"*" + text + "*\"");
		statement.setString(2, text);
		statement.setString(3, text);
		return statement.executeQuery();
	}

	/**
	 * @param radius     radius of the sphere.
	 * @param location   center of the query circle.
	 * @param distance   radius of the query circle.
	 * @param connection an SQL connection.
	 * @return doctors within the specified distance from location.
	 */
	public ResultSet findDoctorsWithinDistance(double radius, Geolocation location, double distance,
			Connection connection) throws java.sql.SQLException {

		Geolocation[] boundingCoordinates = location.boundingCoordinates(distance, radius);
		boolean meridian180WithinDistance = boundingCoordinates[0].getLongitudeInDegrees() > boundingCoordinates[1]
				.getLongitudeInDegrees();

		PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM dbo.doctor WHERE (latitude >= ? AND latitude <= ?) AND (longitude >= ? "
						+ (meridian180WithinDistance ? "OR" : "AND") + " longitude <= ?) AND "
						+ "acos(sin(?) * sin(latitude) + cos(?) * cos(latitude) * cos(longitude - ?)) <= ?");
		statement.setDouble(1, boundingCoordinates[0].getLatitudeInDegrees());
		statement.setDouble(2, boundingCoordinates[1].getLatitudeInDegrees());
		statement.setDouble(3, boundingCoordinates[0].getLongitudeInDegrees());
		statement.setDouble(4, boundingCoordinates[1].getLongitudeInDegrees());
		statement.setDouble(5, location.getLatitudeInDegrees());
		statement.setDouble(6, location.getLatitudeInDegrees());
		statement.setDouble(7, location.getLongitudeInDegrees());
		statement.setDouble(8, distance / radius);

		return statement.executeQuery();
	}

	public ResultSet findDoctorsWithinDistanceAndSpeciality(double radius, Geolocation location, double distance,
			String speciality, Connection connection) throws java.sql.SQLException {

		Geolocation[] boundingCoordinates = location.boundingCoordinates(distance, radius);
		boolean meridian180WithinDistance = boundingCoordinates[0].getLongitudeInDegrees() > boundingCoordinates[1]
				.getLongitudeInDegrees();

		PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM dbo.doctor WHERE (specialty = ?) AND (latitude >= ? AND latitude <= ?) AND (longitude >= ? "
						+ (meridian180WithinDistance ? "OR" : "AND") + " longitude <= ?) AND "
						+ "acos(sin(?) * sin(latitude) + cos(?) * cos(latitude) * cos(longitude - ?)) <= ?");
		statement.setString(1, speciality);
		statement.setDouble(2, boundingCoordinates[0].getLatitudeInDegrees());
		statement.setDouble(3, boundingCoordinates[1].getLatitudeInDegrees());
		statement.setDouble(4, boundingCoordinates[0].getLongitudeInDegrees());
		statement.setDouble(5, boundingCoordinates[1].getLongitudeInDegrees());
		statement.setDouble(6, location.getLatitudeInDegrees());
		statement.setDouble(7, location.getLatitudeInDegrees());
		statement.setDouble(8, location.getLongitudeInDegrees());
		statement.setDouble(9, distance / radius);
		return statement.executeQuery();
	}

	public ResultSet findDoctorsWithinDistanceAndSpecialityAndText(double radius, Geolocation location, double distance,
			String speciality, String text, Connection connection) throws java.sql.SQLException {

		Geolocation[] boundingCoordinates = location.boundingCoordinates(distance, radius);
		boolean meridian180WithinDistance = boundingCoordinates[0].getLongitudeInDegrees() > boundingCoordinates[1]
				.getLongitudeInDegrees();
		System.out.println("Holaaaaaaa");

		PreparedStatement statement = null;
		if (speciality.contentEquals("any")) {
			System.out.println("Here i am");

			/**
			 * Used a query from the URL to match the search text to first name and last
			 * name
			 * https://stackoverflow.com/questions/14290857/sql-select-where-field-contains-words
			 */
			statement = connection.prepareStatement(
					"SELECT * FROM dbo.doctor WHERE (Contains(firstname, ?) OR CHARINDEX(firstname, ?, 1)<>0  OR CHARINDEX(lastname, ?, 1)<>0) AND (latitude >= ? AND latitude <= ?) AND (longitude >= ? "
							+ (meridian180WithinDistance ? "OR" : "AND") + " longitude <= ?) AND "
							+ "acos(sin(?) * sin(latitude) + cos(?) * cos(latitude) * cos(longitude - ?)) <= ?");
			statement.setString(1, "\"*" + text + "*\"");
			statement.setString(2, text);
			statement.setString(3, text);
			statement.setDouble(4, boundingCoordinates[0].getLatitudeInDegrees());
			statement.setDouble(5, boundingCoordinates[1].getLatitudeInDegrees());
			statement.setDouble(6, boundingCoordinates[0].getLongitudeInDegrees());
			statement.setDouble(7, boundingCoordinates[1].getLongitudeInDegrees());
			statement.setDouble(8, location.getLatitudeInDegrees());
			statement.setDouble(9, location.getLatitudeInDegrees());
			statement.setDouble(10, location.getLongitudeInDegrees());
			statement.setDouble(11, distance / radius);
			System.out.println(statement.toString());
		} else {
			statement = connection.prepareStatement(
					"SELECT * FROM dbo.doctor WHERE (specialty = ?) AND (Contains(firstname, ?) OR CHARINDEX(firstname, ?, 1)<>0  OR CHARINDEX(lastname, ?, 1)<>0) AND (latitude >= ? AND latitude <= ?) AND (longitude >= ? "
							+ (meridian180WithinDistance ? "OR" : "AND") + " longitude <= ?) AND "
							+ "acos(sin(?) * sin(latitude) + cos(?) * cos(latitude) * cos(longitude - ?)) <= ?");
			statement.setString(1, speciality);
			statement.setString(2, "\"*" + text + "*\"");
			statement.setString(3, text);
			statement.setString(4, text);
			statement.setDouble(5, boundingCoordinates[0].getLatitudeInDegrees());
			statement.setDouble(6, boundingCoordinates[1].getLatitudeInDegrees());
			statement.setDouble(7, boundingCoordinates[0].getLongitudeInDegrees());
			statement.setDouble(8, boundingCoordinates[1].getLongitudeInDegrees());
			statement.setDouble(9, location.getLatitudeInDegrees());
			statement.setDouble(10, location.getLatitudeInDegrees());
			statement.setDouble(11, location.getLongitudeInDegrees());
			statement.setDouble(12, distance / radius);
		}

		return statement.executeQuery();

	}

	/**
	 * Method to used to test the db connection.
	 */

	public String DBConnectDL() {
		String url = env.getProperty("connectionstring").toString();
		Connection connection = null;
		Statement statement = null;
		String username = "";
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
			String query = "select * from dbo.demo";
			ResultSet demoresult = statement.executeQuery(query);

			while (demoresult.next()) {
				username = demoresult.getString("username");
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
		return username;
	}

	/**
	 * Retrieves doctor's ratings information from db to show in the search results.
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

}
