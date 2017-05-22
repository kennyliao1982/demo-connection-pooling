package com.appx.demo.connectionpool;

import javax.sql.DataSource;

import org.vibur.dbcp.ViburDBCPDataSource;

public class ViburConnectionPoolingTest extends AbstractConnectionTest {

	@Override
	protected DataSource initDataSource() {

		ViburDBCPDataSource ds = new ViburDBCPDataSource();
		ds.setJdbcUrl(dbProperties.getProperty("jdbc.url"));
		ds.setUsername(dbProperties.getProperty("jdbc.username"));
		ds.setPassword(dbProperties.getProperty("jdbc.password"));

		ds.setPoolInitialSize(1);
	    ds.setPoolMaxSize(1);
	    ds.start();
		return ds;
	}

	@Override
	protected void closeDataSource(DataSource ds) {
		((ViburDBCPDataSource) ds).close();
	}

}
