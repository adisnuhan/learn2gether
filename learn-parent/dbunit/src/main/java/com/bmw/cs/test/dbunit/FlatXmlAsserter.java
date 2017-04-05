//______________________________________________________________________________
//
// Project: fza
//    File: $HeadURL: $
// Version: $Id: $
//______________________________________________________________________________
//
//    Created by: Stefan Knaus, stefan.knaus@sulzer.de
// Creation date: 22.01.2016
//    Changed by: $Author: $
//   Change date: $Date::            #$
//______________________________________________________________________________
//
// Copyright: (C) BMW AG 2016, all rights reserved
//______________________________________________________________________________
package com.bmw.cs.test.dbunit;

import com.bmw.cs.test.dbunit.connect.ConnectionInfo;
import com.bmw.cs.test.dbunit.connect.DbConnector;
import com.bmw.cs.test.dbunit.util.FlatXmlFileHandler;
import com.bmw.cs.test.dbunit.util.IgnoreColumns;
import com.bmw.cs.test.dbunit.util.SortColumns;
import com.bmw.cs.test.dbunit.wrapper.IDatabaseConnection;
import org.dbunit.Assertion;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;

/**
 * <!-- ==================================================================== -->
 */
public class FlatXmlAsserter {

    private final Class<?> referenceClass;

    private final DbConnector connector;

    public FlatXmlAsserter(final Class<?> pReferenceClass, final ConnectionInfo pDbInfo) {
        this.referenceClass = pReferenceClass;
        this.connector = new DbConnector(pDbInfo);
    }

    public FlatXmlAsserter(final Object pReferenceObject, final ConnectionInfo pDbInfo) {
        this(pReferenceObject.getClass(), pDbInfo);
    }

    public FlatXmlAsserter(final Class<?> pReferenceClass, final DbConnector pConnector) {
        this.referenceClass = pReferenceClass;
        this.connector = pConnector;
    }

    public FlatXmlAsserter(final Object pReferenceObject, final DbConnector pConnector) {
        this(pReferenceObject.getClass(), pConnector);
    }

    public void assertTableMatchesXmlFile(final String pTableName, final String pFileName) throws Exception {
        assertTableMatchesXmlFile(pTableName, pFileName, SortColumns.NONE, IgnoreColumns.NONE);
    }

    public void assertTableMatchesXmlFile(final String pTableName, final String pFileName, final SortColumns pSortColumns,
            final IgnoreColumns pIgnoreColumns) throws Exception {
        try (IDatabaseConnection connection = connector.getDBConnection()) {
            ITable actualTab = getTableFromDatabase(connection, pTableName, pSortColumns);

            ITable expectedTab = getTableFromXmlFile(pFileName, pTableName, pSortColumns);

            if (!pIgnoreColumns.isEmpty()) {
                Assertion.assertEqualsIgnoreCols(expectedTab, actualTab, pIgnoreColumns.columns);
            } else {
                Assertion.assertEquals(expectedTab, actualTab, actualTab.getTableMetaData().getColumns());
            }
        }
    }

    public void assertQueryResultMatchesXmlFile(final String pQuery, final String pTable, final String pFileName)
            throws Exception {

        try (IDatabaseConnection connection = connector.getDBConnection()) {
            ITable actualTab = connection.createQueryTable(pTable, pQuery);

            ITable expectedTab = getTableFromXmlFile(pFileName, pTable, SortColumns.NONE);

            Assertion.assertEquals(expectedTab, actualTab);
        }
    }

    private ITable getTableFromDatabase(IDatabaseConnection pConnection, String pTableName,
            final SortColumns pSortColumns) throws Exception {
        ITable tab = pConnection.createTable(pTableName);

        return sortTable(tab, pSortColumns);
    }

    private ITable getTableFromXmlFile(String pFileName, String pTableName, final SortColumns pSortColumns)
            throws Exception {
        IDataSet dataSet = new FlatXmlFileHandler(this.referenceClass).readDataSetFromFile(pFileName);

        ITable tab = dataSet.getTable(pTableName);

        return sortTable(tab, pSortColumns);
    }

    private ITable sortTable(ITable tab, final SortColumns pSortColumns) throws DataSetException {
        ITable retTab = tab;
        if (!pSortColumns.isEmpty()) {
            retTab = new SortedTable(tab, pSortColumns.columns);
            ((SortedTable) retTab).setUseComparable(true);
        }
        return retTab;
    }
}
