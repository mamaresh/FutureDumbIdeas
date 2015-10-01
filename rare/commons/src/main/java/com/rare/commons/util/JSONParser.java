package com.rare.commons.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONParser {

	private static final Logger LOG = LoggerFactory.getLogger(JSONParser.class);

	private JSONParser() {

	}

	public static Map<String, Object> parseJson(JSONObject json) throws JSONException {
		LOG.debug("In #JSONParser #parseJson #start");
		Map<String, Object> map = new HashMap<String, Object>();
		String key = null;
		Iterator<?> itr = json.keys();
		try {
			while (itr.hasNext()) {
				Object obj = itr.next();
				if (obj instanceof String) {
					key = (String) obj;
					map.put(key, json.get(key));
				}
			}
		} catch (JSONException ex) {
			LOG.error("Error in JSON Object ", ex);
			throw ex;
		}
		LOG.debug("In #JSONParser #parseJson #start");
		return map;
	}

	public static JSONObject getJsonFromString(String jsonString) throws JSONException {
		LOG.debug("In #JSONParser #getJsonFromString #start");
		JSONObject jsonObject = new JSONObject(jsonString);
		LOG.debug("In #JSONParser #getJsonFromString #end");
		return jsonObject;
	}

}
