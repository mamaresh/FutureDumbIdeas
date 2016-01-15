package com.rare.server.persistence.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.mapping.MappingManager;
import com.rare.server.database.connector.DatabaseConnector;
import com.rare.server.persistence.UserDao;
import com.rare.server.persistence.accessor.UserAccessor;
import com.rare.server.persistence.entity.User;
import com.rare.server.persistence.error.DaoError;
import com.rare.server.persistence.exception.DaoException;
import com.rare.server.persistence.global.DaoConstants;
import com.rare.server.persistence.input.UserFriendDaoInput;
import com.rare.server.persistence.input.UserRegistrationDaoInput;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	DatabaseConnector databaseConnector;

	@Value(DaoConstants.KEYSPACE)
	String keyspace;

	@Override
	public void insertUser(UserRegistrationDaoInput input) throws DaoException {
		doActualInsert(input);
	}

	@Override
	public List<String> getUserForGivenGoogleId(String googleId) throws DaoException {
		try {
			MappingManager manager = createManager();
			UserAccessor accessor = createAccessor(manager);
			return getListOfFriends(accessor.getUserFriends(googleId));
		} catch (DriverException ex) {
			throw new DaoException(DaoError.DRIVER_EXCEPTION, ex);
		}
	}

	private List<String> getListOfFriends(ResultSet userFriends) {
		List<Row> rows = userFriends.all();
		List<String> friendNames = new ArrayList<>();
		for (Row row : rows) {
			friendNames.add(row.getString(DaoConstants.FRIEND_NAME));
		}
		return friendNames;
	}

	@Override
	public User getUser(String googleId, String googleFriendId) throws DaoException {
		try {
			return new MappingManager(this.getDatabaseConnector().getSession(this.getKeyspace())).mapper(User.class)
					.get(googleId, googleFriendId);
		} catch (DriverException ex) {
			throw new DaoException(DaoError.DRIVER_EXCEPTION, ex);
		}
	}

	@Override
	public void removeUser(String googleId) throws DaoException {
		try {
			MappingManager manager = createManager();
			UserAccessor accessor = createAccessor(manager);
			accessor.deleteUser(googleId);
		} catch (DriverException ex) {
			throw new DaoException(DaoError.DRIVER_EXCEPTION, ex);
		}

	}

	private void doActualInsert(UserRegistrationDaoInput input) throws DaoException {
		User user = getUserFromInput(input);
		try {
			for (UserFriendDaoInput friend : input.getUserFriendDaoInputs()) {
				user.setFriendGoogleId(friend.getId());
				user.setFriendGoogleUrl(friend.getUrl());
				user.setFriendName(friend.getDisplayName());
				new MappingManager(this.getDatabaseConnector().getSession(keyspace)).mapper(User.class).save(user);
			}
		} catch (DriverException ex) {
			throw new DaoException(DaoError.DRIVER_EXCEPTION, ex);
		}
	}

	private User getUserFromInput(UserRegistrationDaoInput input) {
		User user = new User();
		BeanUtils.copyProperties(input, user);
		return user;
	}

	private MappingManager createManager() {
		return new MappingManager(this.getDatabaseConnector().getSession(keyspace));
	}

	private UserAccessor createAccessor(MappingManager manager) {
		return manager.createAccessor(UserAccessor.class);
	}

	public DatabaseConnector getDatabaseConnector() {
		return databaseConnector;
	}

	public void setDatabaseConnector(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}

	public String getKeyspace() {
		return keyspace;
	}

	public void setKeyspace(String keyspace) {
		this.keyspace = keyspace;
	}

}
