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
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITableIterator;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <!-- ====================================================================
 */
public class FlatXmlImporter {

	private final Class<?>		referenceClass;

	private final DbConnector	connector;

	private static final Logger	log	= LoggerFactory.getLogger(FlatXmlImporter.class);

	public FlatXmlImporter(final Class<?> pReferenceClass, final ConnectionInfo pDbInfo) {
		this.referenceClass = pReferenceClass;
		this.connector = new DbConnector(pDbInfo);
	}

	public FlatXmlImporter(final Object pReferenceObject, final ConnectionInfo pDbInfo) {
		this(pReferenceObject.getClass(), pDbInfo);
	}

	public FlatXmlImporter(final Class<?> pReferenceClass, final DbConnector pConnector) {
		this.referenceClass = pReferenceClass;
		this.connector = pConnector;
	}

	public FlatXmlImporter(final Object pReferenceObject, DbConnector pConnector) {
		this(pReferenceObject.getClass(), pConnector);
	}

	public void loadData(final String pFileName) throws Exception {
		final IDataSet dataSet = new FlatXmlFileHandler(this.referenceClass).readDataSetFromFile(pFileName);

		printTableInfo(dataSet, pFileName);

		try (IDatabaseConnection db = connector.getDBConnection()) {
			DatabaseOperation.INSERT.execute(db, dataSet);
		}

        log.info("loadData: finished!");
	}

	private void printTableInfo(final IDataSet dataSet, final String pfileName) throws DataSetException {
		// System.out.println("loadData: loading tables " +
		// Arrays.asList(dataSet.getTableNames()));
		ITableIterator tabIT = dataSet.iterator();
		while (tabIT.next()) {
            log.info("loadData: loaded " + tabIT.getTable().getRowCount() + " rows into table "
					+ tabIT.getTable().getTableMetaData().getTableName() + " from file " + pfileName);
		}
	}
}
