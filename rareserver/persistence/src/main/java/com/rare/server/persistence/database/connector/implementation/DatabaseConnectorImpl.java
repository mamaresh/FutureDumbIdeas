package com.rare.server.persistence.database.connector.implementation;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.InvalidQueryException;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.rare.server.persistence.database.connector.DatabaseConnector;
import com.rare.server.persistence.global.PersistenceConstants;

@Component
public class DatabaseConnectorImpl implements DatabaseConnector {

	private static Cluster cluster;

	private static Session session;

	@Value(PersistenceConstants.CASSANDRA_HOSTS)
	private String[] hosts;

	@Override
	public Session getSession(String keyspace) throws InvalidQueryException, NoHostAvailableException {
		if (session != null) {
			if (!session.getLoggedKeyspace().equals(keyspace)) {
				session = createCluster().connect(keyspace);
			}
		} else {
			session = createCluster().connect(keyspace);
		}
		return session;

	}

	private Cluster createCluster() {
		if (cluster == null) {
			cluster = Cluster.builder().addContactPointsWithPorts(getInetSocketAddress(hosts))
					.withProtocolVersion(ProtocolVersion.V3).build();
			return cluster;
		} else {
			return cluster;
		}
	}

	private Collection<InetSocketAddress> getInetSocketAddress(String[] host) {
		List<InetSocketAddress> inetSocketAddresses = new ArrayList<InetSocketAddress>();
		for (int i = 0; i < host.length; i++) {
			String[] splitHostAndPort = host[i].split(":");
			String individualHost = splitHostAndPort[0];
			Integer port = Integer.valueOf(splitHostAndPort[1]);
			inetSocketAddresses.add(new InetSocketAddress(individualHost, port));
		}
		return inetSocketAddresses;
	}

	public String[] getHosts() {
		return hosts;
	}

	public void setHosts(String[] hosts) {
		this.hosts = hosts;
	}

}
