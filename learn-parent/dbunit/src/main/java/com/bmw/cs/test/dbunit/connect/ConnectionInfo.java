//______________________________________________________________________________
//
// Project: fza-common-test
// File: $HeadURL::                                                           $
// Version: $Id::                                                             $
//______________________________________________________________________________
//
//    Created by: Markus Steindl, markus.steindl@sulzer.de
// Creation date: 07.03.2011
//    Changed by: $Author: $
//   Change date: $Date::             $
//______________________________________________________________________________
//
// Copyright: (C) BMW AG 2011, all rights reserved
//______________________________________________________________________________
package com.bmw.cs.test.dbunit.connect;

import org.dbunit.dataset.datatype.IDataTypeFactory;

/**
 * <!-- ==================================================================== -->
 * Database Connection.

 */
public interface ConnectionInfo {

	/**
	 * <!-- ================================================================ -->
	 * Get the jdbcURL.
	 * 
	 * @return the jdbcURL.
	 */
	String getJdbcURL();

	/**
	 * <!-- ================================================================ -->
	 * Get the jdbcUser.
	 * 
	 * @return the jdbcUser.
	 */
	String getJdbcUser();

	/**
	 * <!-- ================================================================ -->
	 * Get the jdbcPassword.
	 * 
	 * @return the jdbcPassword.
	 */
	String getJdbcPassword();

	/**
	 * <!-- ================================================================ -->
	 * Get the jdbcSchema.
	 * 
	 * @return the jdbcSchema.
	 */
	Schema getJdbcSchema();

	/**
	 * Gets jdbc driver.
	 *
	 * @return the jdbc driver
	 */
	String getJdbcDriver();

	IDataTypeFactory getDataTypeFactory();
}