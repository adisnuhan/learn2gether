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

/**
 * <!-- ==================================================================== -->
 * extends DBUnits own IDatabaseConnection by AutoCloseable
 */

public interface IDatabaseConnection extends org.dbunit.database.IDatabaseConnection, AutoCloseable {
}
