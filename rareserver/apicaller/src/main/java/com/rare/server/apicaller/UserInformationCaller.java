package com.rare.server.apicaller;

import com.rare.server.apicaller.exception.ApiCallerException;
import com.rare.server.apicaller.input.UserInformationInput;
import com.rare.server.apicaller.result.UserInformationResult;

public interface UserInformationCaller {

	public UserInformationResult callGoogleApiToGetPersonalInformation(UserInformationInput input) throws ApiCallerException;

}