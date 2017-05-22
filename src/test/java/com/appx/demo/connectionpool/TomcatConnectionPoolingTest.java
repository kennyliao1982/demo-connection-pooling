package com.appx.demo.connectionpool;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;

public class TomcatConnectionPoolingTest extends AbstractConnectionTest {

	@Override
	protected DataSource initDataSource() {

		PoolProperties p = new PoolProperties();
		p.setUrl(dbProperties.getProperty("jdbc.url"));
		p.setDriverClassName(dbProperties.getProperty("jdbc.driver"));
		p.setUsername(dbProperties.getProperty("jdbc.username"));
		p.setPassword(dbProperties.getProperty("jdbc.password"));
		p.setMaxActive(1);
		p.setInitialSize(1);
		p.setMaxIdle(1);
		p.setMinIdle(1);
		org.apache.tomcat.jdbc.pool.DataSource datasource = new org.apache.tomcat.jdbc.pool.DataSource();
		datasource.setPoolProperties(p);

		return datasource;
	}

	@Override
	protected void closeDataSource(DataSource ds) {
		((org.apache.tomcat.jdbc.pool.DataSource) ds).close();
	}

}
