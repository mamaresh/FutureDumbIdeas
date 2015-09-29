package com.rare.commons.test.constants;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

public class DatabaseConstants {

	public static final String ADDRESS = "Flat A-209, Sruthika Springfields, Singapura Main Road, Jalahalli East, Vidyaranyapura";
	public static final String CITY = "Bangalore";
	public static final String COUNTRY = "India";
	public static final String ID = UUID.randomUUID().toString();
	public static final String LATITUDE = "43.57";
	public static final String UPDATED_LATITUDE = "44.00";
	public static final String LONGITUDE = "44.54";
	public static final String STATE = "Karnataka";
	public static final String ZIPCODE = "560097";

	public static final String FACEBOOKID = "pavanraotk";
	public static final String GOOGLEID = "pavan.rao333@gmail.com";
	public static final String TWITTERID = "pavanraotk";

	public static final int AGE = 25;
	public static final String FIRSTNAME = "Pavan";
	public static final String GENDER = "Male";
	public static final String INITIALS = "T K";
	public static final String LASTNAME = "Rao";
	public static final int UPDATED_AGE = 26;

	public static final Timestamp LASTUPDATED = new Timestamp(Calendar
			.getInstance().getTime().getTime());
	public static final String PASSWORD = "Anfield@777";
	public static final String UPDATED_PASSWORD = "Anfield777";

	public static final String METRIC_DESCRIPTION = "Quality of timeliness is measured here";
	public static final String METRIC_NAME = "Timeliness";
	public static final String UPDATED_METRIC_DESCRIPTION = "Timeliness quality";
	
}
