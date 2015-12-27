package com.rare.server.apicaller.google.api.caller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rare.server.apicaller.UserInformationCaller;
import com.rare.server.apicaller.constants.Constants;
import com.rare.server.apicaller.error.ApiCallerError;
import com.rare.server.apicaller.exception.ApiCallerException;
import com.rare.server.apicaller.input.UserInformationInput;
import com.rare.server.apicaller.result.UserInformationResult;
import com.rare.server.apicaller.urls.GoogleApiUrls;

@Component
public class UserInformationCallerImpl implements UserInformationCaller {

	@Override
	public UserInformationResult callGoogleApiToGetPersonalInformation(UserInformationInput input)
			throws ApiCallerException {
		URL url = generateUrl(input);
		HttpURLConnection conn = createConnection(url);
		BufferedReader br = getBufferedReader(conn);
		return generateUserInformationResult(br);
	}

	private UserInformationResult generateUserInformationResult(BufferedReader br) throws ApiCallerException {
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				result += line;
			}
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			return mapper.readValue(result, UserInformationResult.class);
		} catch (IOException e) {
			throw new ApiCallerException(ApiCallerError.GOOGLE_API_IO_EXCEPTION, e);
		}
	}

	private BufferedReader getBufferedReader(HttpURLConnection conn) throws ApiCallerException {
		try {
			return new BufferedReader(new InputStreamReader((conn.getInputStream())));
		} catch (IOException e) {
			throw new ApiCallerException(ApiCallerError.GOOGLE_API_IO_EXCEPTION, e);
		}
	}

	private HttpURLConnection createConnection(URL url) throws ApiCallerException {
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(Constants.GET);
			conn.setRequestProperty(Constants.ACCEPT, Constants.APPLICATION_JSON);
			if (conn.getResponseCode() != Constants.TWO_HUNDRED) {
				throw new ApiCallerException(ApiCallerError.GOOGLE_API_ERROR);
			}
			return conn;
		} catch (IOException e) {
			throw new ApiCallerException(ApiCallerError.GOOGLE_API_IO_EXCEPTION, e);
		}
	}

	private URL generateUrl(UserInformationInput input) throws ApiCallerException {
		try {
			return new URL(GoogleApiUrls.GOOGLE_USER_INFO_URL + input.getGoogleId()
					+ GoogleApiUrls.GOOGLE_USER_INFO_URL_API_KEY);
		} catch (MalformedURLException e) {
			throw new ApiCallerException(ApiCallerError.GOOGLE_API_URL_ERROR, e);
		}
	}

}
