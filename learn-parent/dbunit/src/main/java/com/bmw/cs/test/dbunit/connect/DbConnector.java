//______________________________________________________________________________
//
// Project: fza
//    File: $HeadURL: $
// Version: $Id: $
//______________________________________________________________________________
//
//    Created by: Stefan Knaus, stefan.knaus@sulzer.de
// Creation date: 21.01.2016
//    Changed by: $Author: $
//   Change date: $Date::            #$
//______________________________________________________________________________
//
// Copyright: (C) BMW AG 2016, all rights reserved
//______________________________________________________________________________
package com.bmw.cs.test.dbunit.connect;

import com.bmw.cs.test.dbunit.wrapper.DatabaseConnection;
import com.bmw.cs.test.dbunit.wrapper.IDatabaseConnection;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * <!-- ==================================================================== -->
 */
public class DbConnector {

	private final DataSource		datasource;
	private final ConnectionInfo	info;
	private final Connection		connection;

	public DbConnector(DataSource pDatasource, ConnectionInfo pInfo) {
		datasource = pDatasource;
		connection = null;
		info = pInfo;

		if (info == null) {
			throw new IllegalArgumentException("ConnectionInfo must not be null");
		}
	}

	public DbConnector(ConnectionInfo pInfo) {
		info = pInfo;
		datasource = null;
		connection = null;

		if (info == null) {
			throw new IllegalArgumentException("ConnectionInfo must not be null");
		}
	}

	public DbConnector(Connection pConnection, ConnectionInfo pInfo) {
		connection = pConnection;
		info = pInfo;
		datasource = null;

		if (info == null) {
			throw new IllegalArgumentException("ConnectionInfo must not be null");
		}
	}

	/**
	 * <!-- ================================================================ -->
	 * 
	 * @param info
	 *            the connection info
	 * @return IDatabaseConnection
	 */
	public IDatabaseConnection getDBConnection() throws SQLException, DatabaseUnitException {
		Connection jdbcConnection = connection == null ? getJdbcConnection() : connection;

		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection, info.getJdbcSchema().name());

		DatabaseConfig config = connection.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, info.getDataTypeFactory());
		config.setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
		config.setProperty(DatabaseConfig.FEATURE_SKIP_ORACLE_RECYCLEBIN_TABLES, true);

		return connection;
	}

	public static IDatabaseConnection getDBConnection(ConnectionInfo pInfo) throws Exception {
		return new DbConnector(pInfo).getDBConnection();
	}

	private Connection getJdbcConnection() throws SQLException {
		Connection jdbcConnection;

		if (datasource == null) {
			jdbcConnection = DriverManager.getConnection(info.getJdbcURL(), info.getJdbcUser(),	info.getJdbcPassword());
		} else {
			jdbcConnection = datasource.getConnection(info.getJdbcUser(), info.getJdbcPassword());
		}
		return jdbcConnection;
	}
}
