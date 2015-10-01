package com.rare.logic;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rare.commons.exception.DatabaseException;
import com.rare.commons.exception.LogicException;
import com.rare.commons.util.JSONParser;
import com.rare.entities.entity.Location;
import com.rare.entities.entity.Person;
import com.rare.entities.entity.SocialNetwork;
import com.rare.entities.manager.PersonDAOImpl;

public class PersonHandler {

	private static final Logger LOG = LoggerFactory.getLogger(PersonHandler.class);

	PersonDAOImpl personDAOImpl;

	public void addPerson(JSONObject personJson) throws LogicException {
		LOG.debug("In #PersonHandler #addPerson #start");
		try {
			Map<String, Object> personMap = JSONParser.parseJson(personJson);
			Person person = getPersonFromMap(personMap);
			String id = getId();
			person = setIdForPerson(person, id);
			Location location = getLocationFromMap(personMap, id);
			SocialNetwork socialNetwork = getSocialNetworkFromMap(personMap, id);
			person.setLocation(location);
			person.setSocialNetworking(socialNetwork);
			this.getPersonDAOImpl().addPerson(person);
		} catch (JSONException ex) {
			throw new LogicException(ex);
		} catch (IllegalAccessException ex) {
			LOG.error("Error in getting map ", ex);
			throw new LogicException(ex);
		} catch (InvocationTargetException ex) {
			LOG.error("Error in invocation ", ex);
			throw new LogicException(ex);
		} catch (DatabaseException ex) {
			throw new LogicException(ex);
		}
		LOG.debug("In #PersonHandler #addPerson #end");
	}

	private Person setIdForPerson(Person person, String id) {
		LOG.debug("In #PersonHandler #setIdForPerson #start");
		person.setId(id);
		LOG.debug("In #PersonHandler #setIdForPerson #end");
		return person;
	}

	private String getId() {
		LOG.debug("In #PersonHandler #getId #start");
		LOG.debug("In #PersonHandler #getId #end");
		return UUID.randomUUID().toString();
	}

	private SocialNetwork getSocialNetworkFromMap(Map<String, Object> personMap, String id)
			throws IllegalAccessException, InvocationTargetException {
		LOG.debug("In #PersonHandler #getSocialNetworkFromMap #start");
		SocialNetwork socialNetwork = new SocialNetwork();
		for (Entry<String, Object> entry : personMap.entrySet()) {
			BeanUtils.setProperty(socialNetwork, entry.getKey(), entry.getValue());
		}
		socialNetwork.setId(id);
		LOG.debug("In #PersonHandler #getSocialNetworkFromMap #end");
		return socialNetwork;
	}

	private Location getLocationFromMap(Map<String, Object> personMap, String id)
			throws IllegalAccessException, InvocationTargetException {
		LOG.debug("In #PersonHandler #getLocationFromMap #start");
		Location location = new Location();
		for (Entry<String, Object> entry : personMap.entrySet()) {
			BeanUtils.setProperty(location, entry.getKey(), entry.getValue());
		}
		location.setId(id);
		LOG.debug("In #PersonHandler #getLocationFromMap #end");
		return location;
	}

	private Person getPersonFromMap(Map<String, Object> personMap)
			throws IllegalAccessException, InvocationTargetException {
		LOG.debug("In #PersonHandler #getPersonFromMap #start");
		Person person = new Person();
		for (Entry<String, Object> entry : personMap.entrySet()) {
			BeanUtils.setProperty(person, entry.getKey(), entry.getValue());
		}
		LOG.debug("In #PersonHandler #getPersonFromMap #start");
		return person;
	}

	public PersonDAOImpl getPersonDAOImpl() {
		return personDAOImpl;
	}

	public void setPersonDAOImpl(PersonDAOImpl personDAOImpl) {
		this.personDAOImpl = personDAOImpl;
	}

}
