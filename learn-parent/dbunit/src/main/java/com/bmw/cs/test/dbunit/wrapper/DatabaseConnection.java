//______________________________________________________________________________
//
// Project: fza
//    File: $HeadURL: $
// Version: $Id: $
//______________________________________________________________________________
//
//    Created by: Stefan Knaus, stefan.knaus@sulzer.de
// Creation date: 25.01.2016
//    Changed by: $Author: $
//   Change date: $Date::            #$
//______________________________________________________________________________
//
// Copyright: (C) BMW AG 2016, all rights reserved
//______________________________________________________________________________
package com.bmw.cs.test.dbunit.wrapper;

import org.dbunit.DatabaseUnitException;

import java.sql.Connection;

/**
 * <!-- ==================================================================== -->
 * extends DBUnits own IDatabaseConnection by AutoCloseable
 */

public class DatabaseConnection extends org.dbunit.database.DatabaseConnection implements IDatabaseConnection {
    public DatabaseConnection(Connection connection, String schema) throws DatabaseUnitException {
        super(connection, schema, false);
    }
}
