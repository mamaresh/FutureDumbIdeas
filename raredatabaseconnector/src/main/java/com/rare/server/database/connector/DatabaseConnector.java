package com.rare.server.database.connector;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.InvalidQueryException;
import com.datastax.driver.core.exceptions.NoHostAvailableException;

public interface DatabaseConnector {
	
	Session getSession(String keyspace) throws InvalidQueryException, NoHostAvailableException;	

}
