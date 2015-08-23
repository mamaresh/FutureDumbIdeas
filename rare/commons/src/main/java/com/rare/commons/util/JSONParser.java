package com.rare.commons.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class JSONParser {

	private JSONParser() {

	}

	public static Map<String, Object> parseJson(JSONObject json)
			throws JSONException {
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
			throw ex;
		}
		return map;
	}

}
