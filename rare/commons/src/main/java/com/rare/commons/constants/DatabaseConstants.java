package com.rare.commons.constants;

public class DatabaseConstants {
	
	public static final String AUTHENTICATION_TABLE = "AUTHENTICATION";
	public static final String FIND_ALL_AUTHENTICATION = "Authentication.findAll";
	public static final String ID = "ID";
	public static final String LASTUPDATED = "LASTUPDATED";
	public static final String PASSWORD = "PASSWORD";
	public static final int LENGTH_OF_ID = 100;
	public static final int LENGTH_OF_PASSWORD = 100;
	
	public static final String LOCATION_TABLE = "LOCATION";
	public static final String FIND_ALL_LOCATION = "Location.findAll";
	public static final String ADDRESS = "ADDRESS";
	public static final String CITY = "CITY";
	public static final String COUNTRY = "COUNTRY";
	public static final String LATITUDE = "LATITUDE";
	public static final String LONGITUDE = "LONGITUDE";
	public static final String STATE = "STATE";
	public static final String ZIPCODE = "ZIPCODE";
	public static final String MAPPEDBY_LOCATION = "location";
	public static final int LENGTH_OF_ADDRESS = 1000;
	public static final int LENGTH_OF_CITY = 100;
	public static final int LENGTH_OF_COUNTRY = 100;
	public static final int LENGTH_OF_LATITUDE = 10;
	public static final int LENGTH_OF_LONGITUDE = 10;
	public static final int LENGTH_OF_STATE = 100;
	public static final int LENGTH_OF_ZIPCODE = 20;
	
	public static final String METRICS_TABLE = "METRICS";
	public static final String FIND_ALL_METRICS = "Metrics.findAll";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final String NAME = "NAME";
	public static final String JOINCOLUMN_ID = "Id";
	public static final String REFERENCED_COLUMN_METRICID = "metricId";
	public static final String MAPPEDBY_METRIC = "metric";
	public static final String WEIGHTAGE = "WEIGHTAGE";
	public static final int LENGTH_OF_DESCRIPTION= 5000;
	public static final int LENGTH_OF_NAME = 100;
	
	public static final String RATING_TABLE = "RATING_TABLE";
	public static final String FIND_ALL_RATING = "Rating.findAll";
	public static final String STARS = "STARS";
	public static final String REFERENCE_COLUMN_RATINGID = "RatingId";
	
}
