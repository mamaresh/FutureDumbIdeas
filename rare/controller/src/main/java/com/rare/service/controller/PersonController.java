package com.rare.service.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rare.commons.constants.ControllerConstants;
import com.rare.commons.exception.LogicException;
import com.rare.commons.util.JSONParser;
import com.rare.logic.PersonHandler;

@RestController
@RequestMapping(value = ControllerConstants.PERSON_CONTROLLER_PATH)
public class PersonController {

	private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	PersonHandler personHandler;

	@RequestMapping(value = ControllerConstants.PERSON_CONTROLLER_ADD_PATH, method = RequestMethod.POST)
	public ResponseEntity<String> addPerson(@RequestBody String personJsonString) {
		LOG.debug("In #PersonController #addPerson #start");
		ResponseEntity<String> responseEntity = null;
		try {
			JSONObject personJson = JSONParser.getJsonFromString(personJsonString);
			this.getPersonHandler().addPerson(personJson);
			responseEntity = new ResponseEntity<String>(ControllerConstants.SUCCESSFULLY_ADDED_USER, HttpStatus.OK);
		} catch (LogicException ex) {
			responseEntity = new ResponseEntity<String>(ControllerConstants.INTERNAL_SERVER_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JSONException e) {
			responseEntity = new ResponseEntity<String>(ControllerConstants.ERROR_IN_INPUT, HttpStatus.BAD_REQUEST);
		}
		LOG.debug("In #PersonController #addPerson #end");
		return responseEntity;
	}

	public PersonHandler getPersonHandler() {
		return personHandler;
	}

	public void setPersonHandler(PersonHandler personHandler) {
		this.personHandler = personHandler;
	}

}
