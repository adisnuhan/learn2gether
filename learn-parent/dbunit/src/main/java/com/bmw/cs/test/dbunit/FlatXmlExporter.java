//______________________________________________________________________________
//
// Project: fza-shist-ejb
//    File: $HeadURL: $
// Version: $Id: FlatXmlExporter.java 4177 2015-11-30 12:56:58Z qx72871 $
//______________________________________________________________________________
//
//    Created by: Stefan Knaus, stefan.knaus@sulzer.de
// Creation date: 15.02.2010
//    Changed by: $Author: $
//   Change date: $Date:: 2015-11-30 #$
//______________________________________________________________________________
//
// Copyright: (C) BMW AG 2010, all rights reserved
//______________________________________________________________________________
package com.bmw.cs.test.dbunit;

import com.bmw.cs.test.dbunit.connect.ConnectionInfo;
import com.bmw.cs.test.dbunit.connect.DbConnector;
import com.bmw.cs.test.dbunit.util.FlatXmlFileHandler;
import com.bmw.cs.test.dbunit.wrapper.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * <!-- ====================================================================
 */
public class FlatXmlExporter {

	private final Class<?> referenceClass;

	private final DbConnector connector;

	private static final Logger log = LoggerFactory.getLogger(FlatXmlExporter.class);

	public FlatXmlExporter(final Class<?> pReferenceClass, final ConnectionInfo pDbInfo) {
		this.referenceClass=pReferenceClass;
		this.connector = new DbConnector(pDbInfo);
	}

	public FlatXmlExporter(final Object pReferenceObject, final ConnectionInfo pDbInfo) {
		this(pReferenceObject.getClass(), pDbInfo);
	}
	
	public FlatXmlExporter(final Class<?> pReferenceClass, final DbConnector pConnector) {
		this.referenceClass=pReferenceClass;
		this.connector = pConnector;
	}

	public FlatXmlExporter(final Object pReferenceObject, final DbConnector pConnector) {
		this(pReferenceObject.getClass(), pConnector);
	}

	public void exportTableAndDependents(final String pTableName, final String pTargetFilename) throws Exception {

		try (IDatabaseConnection connection = connector.getDBConnection()) {

			final String[] depTableNames = TablesDependencyHelper.getAllDependentTables(connection, pTableName);
			final IDataSet dataSet = connection.createDataSet(depTableNames);

			new FlatXmlFileHandler(this.referenceClass).writeDataSetToFile(dataSet, pTargetFilename);
		}

        log.info("table [" + pTableName + "] and dependents exported to file " + new File(pTargetFilename).getAbsolutePath());
	}

	public void exportTableAndDependents(final String pTableName) throws Exception {
		final String defaultFileName = pTableName + "_table_export.xml";
		exportTableAndDependents(pTableName, defaultFileName);
	}

	public void exportSingleTable(final String pTableName, final String pTargetFilename) throws Exception {
		try (IDatabaseConnection connection = connector.getDBConnection()) {
			final QueryDataSet dataSet = new QueryDataSet(connection);
			dataSet.addTable(pTableName);

			new FlatXmlFileHandler(this.referenceClass).writeDataSetToFile(dataSet, pTargetFilename);
		}

        log.info("single table [" + pTableName + "] exported to file " + new File(pTargetFilename).getAbsolutePath());
	}

	public void exportSingleTable(final String pTableName) throws Exception {
		final String defaultFileName = pTableName + "_single_table_export.xml";
		exportSingleTable(pTableName, defaultFileName);
	}

	/**
	 * <!-- ================================================================ -->
	 * Export einer Tabelle (Einschränkung auf Ergebnis einer Query) in Datei
	 * mit angegegebenen Namen.
	 * 
	 * @param pTableName
	 *            Tabellenname
	 * @param pQuery
	 *            Query
	 * @throws Exception
	 *             Fehler
	 */
	public void exportQueryResult(final String pTableName, final String pQuery, final String pTargetFilename)
			throws Exception {
		try (IDatabaseConnection connection = connector.getDBConnection()) {
			final QueryDataSet dataSet = new QueryDataSet(connection);
			dataSet.addTable(pTableName, pQuery);

			new FlatXmlFileHandler(this.referenceClass).writeDataSetToFile(dataSet, pTargetFilename);
		}

        log.info("queryResult from table [" + pTableName + "] exported to file " + new File(pTargetFilename).getAbsolutePath());
	}

    /**
     * Export query result.
     *
     * @param pTableName the p table name
     * @param pQuery     the p query
     * @throws Exception the exception
     */
    public void exportQueryResult(final String pTableName, final String pQuery)
            throws Exception {
        final String defaultFileName = pTableName + "_query_export.xml";
        exportQueryResult(pTableName, pQuery, defaultFileName);
    }

    /**
     * <!-- ================================================================ --> Export einer Tabelle (Einschränkung auf
     * Ergebnis einer Query) in Datei mit angegegebenen Namen.
     *
     * @param pTableName Tabellenname e.g  FZA.SERVICEFALL_EVENT
     * @param pWhereClause  where clause to be appened to all select * from table queries e.g.  where fgnr7 = 'B123456';
     * @throws Exception Fehler
     */
    public void exportQueryResultForTableAndDependents(final String pTableName, final String pWhereClause, final String
            pTargetFilename)
            throws Exception {
        final String queryTemplate = "select * from %s %s";

        try (IDatabaseConnection connection = connector.getDBConnection()) {
            final String[] depTableNames = TablesDependencyHelper.getDependsOnTables(connection, pTableName);
            final QueryDataSet dataSet = new QueryDataSet(connection);
            String query;
            // As depTableNames are in reversed order iterate over array also in reverse order
            for (int i = depTableNames.length; i > 0; i--) {
                query = String.format(queryTemplate, depTableNames[i - 1], pWhereClause);
                log.info("adding query: " + query);
                dataSet.addTable(depTableNames[i - 1], query);
            }

            new FlatXmlFileHandler(this.referenceClass).writeDataSetToFile(dataSet, pTargetFilename);
        }

        log.info("queryResult from table [" + pTableName + "] and its dependents exported to file " + new File
            (pTargetFilename).getAbsolutePath());
    }

    /**
     * <!-- ================================================================ --> Export einer Tabelle (Einschränkung auf
     * Ergebnis einer Query) in Datei mit angegegebenen Namen.
     *
     * @param pTableName Tabellenname e.g  FZA.SERVICEFALL_EVENT
     * @param pWhereClause     where clause to be appened to all select * from table queries e.g.  where fgnr7 = 'B123456';
     * @throws Exception Fehler
     */
    public void exportQueryResultForTableAndDependents(final String pTableName, final String pWhereClause)
            throws Exception {

        exportQueryResultForTableAndDependents(pTableName, pWhereClause, pTableName + "_query_export_with_dependents" +
                ".xml");
    }
}