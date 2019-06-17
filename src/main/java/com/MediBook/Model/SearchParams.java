package com.MediBook.Model;

public class SearchParams {
	private String latitude;
	private String longitude;
	private String speciality;
	private String searchText;

	public SearchParams(String latitude, String longitude, String speciality, String searchText) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.speciality = speciality;
		this.searchText = searchText;
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

	/**
	 * @return the speciality
	 */
	public String getSpeciality() {
		return speciality;
	}

	/**
	 * @param speciality the speciality to set
	 */
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	/**
	 * @return the searchText
	 */
	public String getSearchText() {
		return searchText;
	}

	/**
	 * @param searchText the searchText to set
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String toString() {
		return "Latitude: " + this.latitude + " Longitude: " + this.longitude + " Speciality: " + this.speciality
				+ " Search Text: " + this.searchText + "\n";
	}

}
