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

import org.apache.commons.lang.StringUtils;
import org.dbunit.dataset.datatype.IDataTypeFactory;
import org.dbunit.ext.h2.H2DataTypeFactory;

/**
 * <!-- ==================================================================== -->
 * Verbindung zur H2 Datenbank
 *
 * @author <a href="mailto:stefan.knaus@sulzer.de">Stefan Knaus</a>
 *         / <a href="http://www.sulzer.de" target="_blank">Sulzer GmbH</a>
 * @version $Revision: 4200 $ - $Author: $ - $Date:: 2016-01-18 #$
 * @since: Release 1.0
 */
public enum H2Connections implements ConnectionInfo {

	MAIN_SCHEMA(Constants.NO_USERNAME, Constants.NO_PASSWORD, Schema.LEARN2GETHER);


	private static class Constants {
	    private static final String H2_DRIVER_CLASS = "org.persistence.Driver";
		private static final String URL = "jdbc:h2:mem:test;MODE=MYSQL";
		private static final String NO_USERNAME= StringUtils.EMPTY;
		private static final String NO_PASSWORD= StringUtils.EMPTY;
        private Constants(){/*hide constructor*/ }
	}

	private String jdbcUser;
	private String jdbcPassword;
	private Schema jdbcSchema;

	/**
	 * Instantiates a new Test db connections.
	 *
	 * @param pJdbcUser
	 *            the p jdbc user
	 * @param pJdbcPassword
	 *            the p jdbc password
	 * @param pJdbcSchema
	 *            the p jdbc schema
	 */
	H2Connections(final String pJdbcUser, final String pJdbcPassword, final Schema pJdbcSchema) {
		jdbcUser = pJdbcUser;
		jdbcPassword = pJdbcPassword;
		jdbcSchema = pJdbcSchema;
	}

	/**
	 * <!-- ================================================================ -->
	 * overrides / implements getJdbcURL.
	 *
	 * @return JDBC Url
	 */
	@Override
	public String getJdbcURL() {
		return Constants.URL;
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
		return Constants.H2_DRIVER_CLASS;
	}

    @Override
    public IDataTypeFactory getDataTypeFactory() { return new H2DataTypeFactory(); }
}
