package com.rare.server.database.connector.implementation;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.InvalidQueryException;
import com.rare.server.database.connector.DatabaseConnector;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({
        CassandraUnitTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class })
@CassandraDataSet(value = { "keyspace.cql" })
@EmbeddedCassandra
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DatabaseConnectorImplTest {

    @Autowired
    public DatabaseConnector connector;
    Session                  session;

    @Test
    public void test() {
        session = this.getConnector().getSession("rare");
        String initialHashCode = session.toString();
        assertThat(session, notNullValue());
        session = this.getConnector().getSession("rare");
        String laterHashCode = session.toString();
        assertThat(initialHashCode, equalTo(laterHashCode));
    }

    @Test
    public void testInvalidCase() {
        try {
            session = connector.getSession("pms_core_invalid_test");
            fail("Exception not thrown");
        } catch (InvalidQueryException ex) {
        }
    }

    /**
     * @return the connector
     */
    public DatabaseConnector getConnector() {
        return connector;
    }

    /**
     * @param connector the connector to set
     */
    public void setConnector(DatabaseConnector connector) {
        this.connector = connector;
    }

}
