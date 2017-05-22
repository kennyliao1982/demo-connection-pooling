package com.appx.demo.connectionpool;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

public class NoConnectionPoolingTest extends AbstractConnectionTest {

	@Override
	protected DataSource initDataSource() {
		
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL(dbProperties.getProperty("jdbc.url"));
		ds.setUser(dbProperties.getProperty("jdbc.username"));
		ds.setPassword(dbProperties.getProperty("jdbc.password"));

		return ds;
	}
}
