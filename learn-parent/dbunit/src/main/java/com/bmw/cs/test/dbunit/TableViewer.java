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
import com.bmw.cs.test.dbunit.wrapper.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 * <!-- ==================================================================== -->
 * small helper class to print the result of a query to System.out
 * ideally used during creation/debugging of a unit test
 */

public class TableViewer {

	private final DbConnector connector;

	public TableViewer(final ConnectionInfo pDbInfo) {
		this.connector = new DbConnector(pDbInfo);
	}

	public TableViewer(final DbConnector pConnector) {
		this.connector = pConnector;
	}

	public void printQuery(String pTableName, String pQuery) throws Exception {
		try (IDatabaseConnection connection = connector.getDBConnection()) {

			QueryDataSet qds = new QueryDataSet(connection);
			qds.addTable(pTableName, pQuery);
			FlatXmlDataSet.write(createReplacementDataSet(qds), System.out);
		}
	}

	private IDataSet createReplacementDataSet(final IDataSet pDataSet) {
		final ReplacementDataSet replacementDataSet = new ReplacementDataSet(pDataSet);
		replacementDataSet.addReplacementObject(null, "[NULL]");
		return replacementDataSet;
	}

	public void printTable(String pTableName) throws Exception {
		try (IDatabaseConnection connection = connector.getDBConnection()) {

			QueryDataSet qds = new QueryDataSet(connection);
			qds.addTable(pTableName, "SELECT * FROM " + pTableName);

			FlatXmlDataSet.write(createReplacementDataSet(qds), System.out);
		}
	}

	public void printSchema() throws Exception {
		try (IDatabaseConnection connection = connector.getDBConnection()) {
			FlatXmlDataSet.write(createReplacementDataSet(connection.createDataSet()), System.out);
		}
	}
}
