//______________________________________________________________________________
//
// Project: fza
//    File: $HeadURL: $
// Version: $Id: $
//______________________________________________________________________________
//
//    Created by: Stefan Knaus, stefan.knaus@sulzer.de
// Creation date: 21.01.2016
//    Changed by: $Author: $
//   Change date: $Date::            #$
//______________________________________________________________________________
//
// Copyright: (C) BMW AG 2016, all rights reserved
//______________________________________________________________________________
package com.bmw.cs.test.dbunit;

import java.util.Arrays;
import java.util.List;

import com.bmw.cs.test.dbunit.connect.ConnectionInfo;
import com.bmw.cs.test.dbunit.connect.DbConnector;
import com.bmw.cs.test.dbunit.connect.H2Connections;
import com.bmw.cs.test.persistence.AbstractH2UnitTest;

/**
 * <!-- ==================================================================== -->
 * Testclass for {@link FlatXmlImporter}
 * 
 * @author <a href="mailto:stefan.knaus@sulzer.de">Stefan Knaus</a>
 *         / <a href="http://www.sulzer.de" target="_blank">Sulzer GmbH</a>
 */
public class FlatXmlImporterTest extends AbstractH2UnitTest {

	@Override
	protected ConnectionInfo getConnectionInfo() {
		return H2Connections.MAIN_SCHEMA;
	}

	@Override
	protected List<String> getDDLFileNames() {
		return Arrays.asList(new String[] { "/ddl/create_table_AFZ_DATENQUELLE.sql" });
	}

//	@Test
	public void testLoadData() throws Exception {
		FlatXmlImporter imp = new FlatXmlImporter(this, H2Connections.MAIN_SCHEMA);

		imp.loadData("FZA.AFZ_DATENQUELLE_testdata.xml");
	}

//	@Test
	public void testLoadDataFromSubDir() throws Exception {
		FlatXmlImporter imp = new FlatXmlImporter(this, H2Connections.MAIN_SCHEMA);

		imp.loadData("subdir/FZA.AFZ_DATENQUELLE_testdata2.xml");
	}

//	@Test
	public void testLoadDataUsingConnector() throws Exception {
		DbConnector connector = new DbConnector(H2Connections.MAIN_SCHEMA);
		
		FlatXmlImporter imp = new FlatXmlImporter(this, connector);

		imp.loadData("FZA.AFZ_DATENQUELLE_testdata.xml");
	}

//	@Test
	public void testLoadDataFromSubDirUsingConnector() throws Exception {
		DbConnector connector = new DbConnector(H2Connections.MAIN_SCHEMA);
		
		FlatXmlImporter imp = new FlatXmlImporter(this, connector);

		imp.loadData("subdir/FZA.AFZ_DATENQUELLE_testdata2.xml");
	}
}