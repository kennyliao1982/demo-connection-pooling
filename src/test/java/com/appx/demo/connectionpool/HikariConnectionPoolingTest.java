package com.appx.demo.connectionpool;

import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class HikariConnectionPoolingTest extends AbstractConnectionTest {

	@Override
	protected DataSource initDataSource() {

		HikariDataSource ds = new HikariDataSource();
		ds.setDataSourceClassName(dbProperties.getProperty("jdbc.dataSourceClassName"));
		ds.setMinimumPoolSize(1);
		ds.setMaximumPoolSize(1);

		Properties props = new Properties();
		props.put("url", dbProperties.getProperty("jdbc.url"));
		props.put("user", dbProperties.getProperty("jdbc.username"));
		props.put("password", dbProperties.getProperty("jdbc.password"));
		ds.setDataSourceProperties(props);

		return ds;
	}

	@Override
	protected void closeDataSource(DataSource ds) {
		((HikariDataSource) ds).close();
	}

}
