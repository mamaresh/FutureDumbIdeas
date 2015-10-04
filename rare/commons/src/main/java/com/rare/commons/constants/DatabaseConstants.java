package com.rare.commons.constants;

public class DatabaseConstants {
	
	public static final String AUTHENTICATION_TABLE = "AUTHENTICATION";
	public static final String FIND_ALL_AUTHENTICATION = "Authentication.findAll";
	public static final String ID = "ID";
	public static final String LASTUPDATED = "LASTUPDATED";
	public static final String PASSWORD = "PASSWORD";
	public static final int LENGTH_OF_ID = 100;
	public static final int LENGTH_OF_PASSWORD = 100;
	
	public static final String PERSON_TABLE = "PERSON";
	public static final String FIND_ALL_PERSON = "Person.findAll";
	public static final String AGE = "AGE";
	public static final String FIRSTNAME = "FIRSTNAME";
	public static final String GENDER = "GENDER";
	public static final String INITIALS = "INITIALS";
	public static final String LASTNAME = "LASTNAME";
	public static final String MAPPEDBY_PERSON = "person";
	public static final int LENGTH_OF_FIRSTNAME = 100;
	public static final int LENGTH_OF_GENDER = 100;
	public static final int LENGTH_OF_INITIALS = 100;
	public static final int LENGTH_OF_LASTNAME = 100;
	
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
	public static final String JOINCOLUMN_ID = "id";
	public static final String REFERENCED_COLUMN_METRICID = "metricId";
	public static final String MAPPEDBY_METRIC = "metric";
	public static final String WEIGHTAGE = "WEIGHTAGE";
	public static final int LENGTH_OF_DESCRIPTION= 5000;
	public static final int LENGTH_OF_NAME = 100;
	
	public static final String RATING_TABLE = "RATING";
	public static final String FIND_ALL_RATING = "Rating.findAll";
	public static final String STARS = "STARS";
	public static final String REFERENCE_COLUMN_RATINGID = "RatingId";
	
	public static final String SERVICE_TABLE = "SERVICE";
	public static final String FIND_ALL_SERVICES = "Service.findAll";
	public static final String PHONE_NUMBER = "PHONENUMBER";
	public static final String PROFILE_CONTENT = "PROFILECONTENT";
	public static final String WEBSITE_URL = "WEBSITEURL";
	public static final String MAPPEDBY_SERVICE = "service";
	public static final String JOINCOLUMN_PERSONID = "personId";
	public static final String REFERENCE_COLUMN_SERVICEID = "ServiceId";
	public static final int LENGTH_OF_PHONE_NUMBER = 13;
	public static final int LENGTH_OF_PROFILE_CONTENT = 5000;
	public static final int LENGTH_OF_WEBSITE_URL = 1000;
	
	public static final String SERVICECATEGORY_TABLE = "SERVICECATEGORY";
	public static final String FIND_ALL_SERVICE_CATEGORIES = "ServiceCategory.findAll";
	public static final String MAPPEDBY_SERVICECATEGORY = "serviceCategory";
	
	public static final String SOCIAL_NETWORK_TABLE = "SOCIALNETWORK";
	public static final String FIND_ALL_SOCIAL_NETWORKS = "SocialNetwork.findAll";
	public static final String FACEBOOK = "FACEBOOK";
	public static final String GOOGLE = "GOOGLE";
	public static final String TWITTER = "TWITTER";
	public static final String MAPPEDBY_SOCIALNETWORK = "socialNetwork";
	public static final int LENGTH_OF_FACEBOOK = 100;
	public static final int LENGTH_OF_GOOGLE = 100;
	public static final int LENGTH_OF_TWITTER = 100;
	
}
