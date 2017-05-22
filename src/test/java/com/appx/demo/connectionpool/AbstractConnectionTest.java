package com.appx.demo.connectionpool;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractConnectionTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractConnectionTest.class);

	private static final int MAX_ITERATIONS = 5000;

	private static Server dbServer;

	protected static Properties dbProperties = new Properties();

	@BeforeClass
	public static void initTest() throws SQLException, IOException {
		// start the h2 database server
		dbServer = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8043").start();

		// load database properties
		dbProperties.load(AbstractConnectionTest.class.getResourceAsStream("/jdbc.properties"));
	}

	protected abstract DataSource initDataSource();

	@Test
	public final void testOpenCloseConnections() throws SQLException {
		DataSource ds = initDataSource();

		long[] timeRecords = new long[MAX_ITERATIONS];

		for (int i = 0; i < MAX_ITERATIONS; i++) {
			long startTime = System.currentTimeMillis();

			// open the connection and close it immediately
			ds.getConnection().close();

			timeRecords[i] = System.currentTimeMillis() - startTime;
		}

		double avg = Arrays.stream(timeRecords).average().getAsDouble();
		long sum = Arrays.stream(timeRecords).sum();

		LOGGER.info("open/close connection {} times average: {} ms, total {} ms", MAX_ITERATIONS, avg, sum);

		closeDataSource(ds);
	}

	protected void closeDataSource(DataSource ds) {
	}

	@AfterClass
	public static void closeTest() {
		dbServer.stop();
	}
}
