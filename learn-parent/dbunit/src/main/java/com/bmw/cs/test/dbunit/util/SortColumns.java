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
package com.bmw.cs.test.dbunit.util;

import org.apache.commons.lang.ArrayUtils;

public class SortColumns {

    public String[] columns;

    public static final SortColumns NONE = new SortColumns(){};

    public SortColumns(final String... pCols) {
        columns=pCols;
    }

    public boolean isEmpty() {return columns==null || ArrayUtils.isEmpty(columns);}
}
