package com.rare.server.persistence.accessor;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.rare.server.persistence.global.DaoConstants;

@Accessor
public interface UserAccessor {

	@Query("SELECT " + DaoConstants.FRIEND_NAME + " FROM " + DaoConstants.USER_TABLE + " WHERE "
			+ DaoConstants.GOOGLE_ID + " = :googleId")
	public ResultSet getUserFriends(String googleId);

	@Query("DELETE FROM " + DaoConstants.USER_TABLE + " WHERE " + DaoConstants.GOOGLE_ID + " = :googleId")
	public void deleteUser(String googleId);

}
