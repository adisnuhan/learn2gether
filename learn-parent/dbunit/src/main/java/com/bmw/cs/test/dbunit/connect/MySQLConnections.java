//______________________________________________________________________________
//
// Project: fza-common-test
//    File: $HeadURL: $
// Version: $Id: OracleConnections.java 4200 2016-01-18 17:46:47Z qxh1082 $
//______________________________________________________________________________
//
//    Created by: Stefan Knaus, stefan.knaus@sulzer.de
// Creation date: 18.02.2010
//    Changed by: $Author: $
//   Change date: $Date:: 2016-01-18 #$
//______________________________________________________________________________
//
// Copyright: (C) BMW AG 2010, all rights reserved
//______________________________________________________________________________
package com.bmw.cs.test.dbunit.connect;

import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;

/**
 * <!-- ==================================================================== -->
 * Verbindungen zu den Testdatenbanken.
 */
public enum MySQLConnections implements ConnectionInfo {



    MAIN_SCHEMA("root", "root", Schema.LEARN2GETHER, JdbcURL.L2G_MAIN);


	private static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";

	private final String jdbcUser;
	private final String jdbcPassword;
	private final Schema jdbcSchema;
	private final String jdbcURL;


    /**
     * Construct an instance of OracleConnections.
     * @param pJdbcUser Database User
     * @param pJdbcPassword Database password
     * @param pJdbcSchema Database Schema
     * @param pJdbcURL  JDBC URL
     */
	MySQLConnections(final String pJdbcUser, final String pJdbcPassword, final Schema pJdbcSchema,
                     final JdbcURL pJdbcURL) {
        jdbcUser = pJdbcUser;
        jdbcPassword = pJdbcPassword;
        jdbcSchema = pJdbcSchema;
        jdbcURL = pJdbcURL.getValue();
    }

    /**
	 * <!-- ================================================================ -->
	 * overrides / implements getJdbcURL.
	 *
	 * @return JDBC Url
	 */
    @Override
	public String getJdbcURL() {
		return jdbcURL;
	}

	/**
	 * <!-- ================================================================ -->
	 * overrides / implements getJdbcUser.
	 *
	 * @return Database User
	 */
    @Override
	public String getJdbcUser() {
		return jdbcUser;
	}

	/**
	 * <!-- ================================================================ -->
	 * overrides / implements getJdbcPassword.
	 *
	 * @return Database Password
	 */
    @Override
	public String getJdbcPassword() {
		return jdbcPassword;
	}

	/**
	 * <!-- ================================================================ -->
	 * overrides / implements getJdbcSchema.
	 *
	 * @return Schema
	 */
    @Override
	public Schema getJdbcSchema() {
		return jdbcSchema;
	}

	/**
	 * Gets jdbc driver.
	 *
	 * @return the jdbc driver
	 */
    @Override
	public String getJdbcDriver() {
		return MYSQL_DRIVER_CLASS;
	}

    @Override
	public IDataTypeFactory getDataTypeFactory() { return new MySqlDataTypeFactory(); }
}
