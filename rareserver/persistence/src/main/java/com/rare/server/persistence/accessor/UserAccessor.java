package com.rare.server.persistence.accessor;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.rare.server.persistence.global.PersistenceConstants;

@Accessor
public interface UserAccessor {

	/*
	 * SELECT friend_display_name FROM user WHERE google_id = :googleId;
	 */
	@Query("SELECT " + PersistenceConstants.FRIEND_NAME + " FROM " + PersistenceConstants.USER_TABLE + " WHERE "
			+ PersistenceConstants.GOOGLE_ID + " = :googleId")
	public Result<String> getUserFriends(String googleId);
	
	/*
	 * DELETE FROM user WHERE google_id = :googleId;
	 */
	@Query("DELETE FROM " + PersistenceConstants.USER_TABLE + " WHERE " + PersistenceConstants.GOOGLE_ID + " = :googleId")
	public void deleteUser(String googleId);

}
