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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmw.cs.test.dbunit.connect.ConnectionInfo;
import com.bmw.cs.test.dbunit.connect.DbConnector;
import com.bmw.cs.test.dbunit.wrapper.IDatabaseConnection;

/**
 * <!-- ==================================================================== -->
 * small helper class to print the result of a query to System.out
 * ideally used during creation/debugging of a unit test
 */

public class HtmlTableExporter {

	private static final Logger	log	= LoggerFactory.getLogger(FlatXmlExporter.class);
	private final DbConnector	connector;

	public HtmlTableExporter(final ConnectionInfo pDbInfo) {
		this.connector = new DbConnector(pDbInfo);
	}

	public HtmlTableExporter(final DbConnector pConnector) {
		this.connector = pConnector;
	}

	public void exportQuery(String pTableName, String pQuery, final String pTargetFilename) throws Exception {
		try (IDatabaseConnection connection = connector.getDBConnection();
				ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);) {

			QueryDataSet qds = new QueryDataSet(connection);
			qds.addTable(pTableName, pQuery);

			FlatXmlDataSet.write(createReplacementDataSet(qds), bos);
			writeHTMLFile(pTargetFilename, bos.toByteArray());

			log.info("schema " + connection.getSchema() + " exported to file "
					+ new File(pTargetFilename).getAbsolutePath());
		}
	}

	private void writeHTMLFile(final String pTargetFilename, final byte[] pXMLContent) throws Exception {

		try (FileOutputStream fos = new FileOutputStream(pTargetFilename);) {

			SAXTransformerFactory stf = (SAXTransformerFactory) TransformerFactory.newInstance();

			Templates templates1 = stf
					.newTemplates(new StreamSource(getClass().getResourceAsStream("/dataset2html.xsl")));
			TransformerHandler th1 = stf.newTransformerHandler(templates1);
			th1.setResult(new StreamResult(fos));

			Transformer t = stf.newTransformer();
			t.transform(new StreamSource(new ByteArrayInputStream(pXMLContent)), new SAXResult(th1));
		}
	}

	private IDataSet createReplacementDataSet(final IDataSet pDataSet) {
		final ReplacementDataSet replacementDataSet = new ReplacementDataSet(pDataSet);
		replacementDataSet.addReplacementObject(null, "[NULL]");
		return replacementDataSet;
	}

	public void exportTable(String pTableName, final String pTargetFilename) throws Exception {
		try (IDatabaseConnection connection = connector.getDBConnection();
				ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);) {

			QueryDataSet qds = new QueryDataSet(connection);
			qds.addTable(pTableName, "SELECT * FROM " + pTableName);

			FlatXmlDataSet.write(createReplacementDataSet(qds), bos);
			writeHTMLFile(pTargetFilename, bos.toByteArray());

			log.info("schema " + connection.getSchema() + " exported to file "
					+ new File(pTargetFilename).getAbsolutePath());
		}
	}

	public void exportSchema(final String pTargetFilename) throws Exception {
		try (IDatabaseConnection connection = connector.getDBConnection();
				ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);) {

			FlatXmlDataSet.write(createReplacementDataSet(connection.createDataSet()), bos);
			writeHTMLFile(pTargetFilename, bos.toByteArray());

			log.info("schema " + connection.getSchema() + " exported to file "
					+ new File(pTargetFilename).getAbsolutePath());

		}
	}
}
