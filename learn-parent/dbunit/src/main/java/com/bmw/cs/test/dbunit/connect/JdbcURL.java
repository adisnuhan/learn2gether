//______________________________________________________________________________
//
// Project: fza
//    File: $HeadURL: https://lpintrae.muc:4756/svn/fza/code/trunk/build/fza/dbunit/src/main/java/com/bmw/cs/test/dbunit/connect/JdbcURL.java $
// Version: $Id: JdbcURL.java 5057 2017-03-30 04:50:00Z qx72871 $
//______________________________________________________________________________
//
//    Created by: Stefan Knaus, stefan.knaus@sulzer.de
// Creation date: 30.03.2017
//    Changed by: $Author: qx72871 $
//   Change date: $Date:: 2017-03-30 #$
//______________________________________________________________________________
//
// Copyright: (C) BMW AG 2017, all rights reserved
//______________________________________________________________________________
package com.bmw.cs.test.dbunit.connect;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines JDBC-Connection URLs
 */

public enum JdbcURL {

    L2G_MAIN("jdbc:mysql://localhost:3306/learn2gether"),
    UNKNOWN("UNKOWN");

    /** The enumMap. */
    private static Map<String, JdbcURL> enumMap = new HashMap<>();

    static {
        for (JdbcURL enumObj : JdbcURL.values()) {
            enumMap.put(enumObj.value, enumObj);
        }
    }


    /** The Value. */
    private final String value;

    /**
     * <!-- ================================================================ -->
     * Construct an instance of JdbcURL
     *
     * @param pValue the value
     */
    JdbcURL(final String pValue) {
        value = pValue;
    }

    /**
     * <!-- ================================================================ -->
     * Get the value.
     *
     * @return the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * <!-- ================================================================ -->
     * overrides / implements toString
     *
     * @return String representation of the Permission
     * @see Enum#toString()
     */
    @Override
    public String toString() {
        return "JdbcURL [" + value + "] ";
    }

    /**
     * <!-- ================================================================ -->
     * Get JdbcURL from Value
     *
     * @param pValue the enum value
     * @return JdbcURL or JdbcURL.UNKNOWN if no enum for pValue exists
     */
    public static JdbcURL fromValue(final String pValue) {
        if (enumMap.containsKey(pValue)) {
            return enumMap.get(pValue);
        } else {
            return UNKNOWN;
        }
    }


}
