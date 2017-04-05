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
package com.bmw.cs.test.dbunit.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITableIterator;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;

import com.bmw.cs.test.dbunit.connect.ConnectionInfo;
import com.bmw.cs.test.dbunit.connect.DbConnector;
import org.xml.sax.InputSource;

/**
 * <!-- ====================================================================
 *
 */
public class FlatXmlFileHandler {

	// used for class loading
	private Class baseClass;

	public FlatXmlFileHandler(Class pBaseClass) {
		this.baseClass = pBaseClass;
	}

	public IDataSet readDataSetFromFile(final String pFileName) throws Exception {
		final URL fullFileURL = baseClass.getResource(pFileName);
		if (fullFileURL == null) {
			throw new FileNotFoundException("File not found: " + pFileName + ", remember: we search under "
					+ baseClass.getResource(".") + "!");
		}

		try (InputStream sqlInputStream = baseClass.getResourceAsStream(pFileName)) {
			final IDataSet dataSet = new FlatXmlDataSetBuilder().build(sqlInputStream);
            final ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet);
            replacementDataSet.addReplacementObject("[NULL]", null);
            return replacementDataSet;
        }
    }

	public void writeDataSetToFile(final IDataSet pDataSet, final String pTargetFilename) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(pTargetFilename)) {
            final ReplacementDataSet replacementDataSet = new ReplacementDataSet(pDataSet);
            replacementDataSet.addReplacementObject(null, "[NULL]");
            FlatXmlDataSet.write(replacementDataSet, fos);
		}
    }
}
