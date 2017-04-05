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
import com.bmw.cs.test.dbunit.connect.H2Connections;
import com.bmw.cs.test.dbunit.util.IgnoreColumns;
import com.bmw.cs.test.dbunit.util.SortColumns;
import com.bmw.cs.test.persistence.AbstractH2UnitTest;

import java.util.Arrays;
import java.util.List;

/**
 * <!-- ==================================================================== -->
 *
 * @author <a href="mailto:stefan.knaus@sulzer.de">Stefan Knaus</a>
 *         / <a href="http://www.sulzer.de" target="_blank">Sulzer GmbH</a>
 */
public class FlatXmlAsserterTest extends AbstractH2UnitTest {

    @Override
    protected ConnectionInfo getConnectionInfo() {
        return H2Connections.MAIN_SCHEMA;
    }

    @Override
    protected List<String> getDDLFileNames() {
        return Arrays.asList(new String[] { "/ddl/create_table_AFZ_DATENQUELLE.sql" });
    }

//    @Test
    public void testTableMatchesXmlFile() throws Exception {
        // setup
        FlatXmlImporter imp = new FlatXmlImporter(this, H2Connections.MAIN_SCHEMA);
        imp.loadData("FZA.AFZ_DATENQUELLE_testdata.xml");

        // action
        FlatXmlAsserter ass = new FlatXmlAsserter(this, H2Connections.MAIN_SCHEMA);
        ass.assertTableMatchesXmlFile("FZA.AFZ_DATENQUELLE", "FZA.AFZ_DATENQUELLE_result.xml",
            SortColumns.NONE, new IgnoreColumns("QUALITAET"));
    }
    
//	@Test
	public void testQueryResultMatchesXmlFile() throws Exception {
		// setup
		FlatXmlImporter importer = new FlatXmlImporter(this, H2Connections.MAIN_SCHEMA);
		importer.loadData("FZA.AFZ_DATENQUELLE_testdata.xml");

		// action
		FlatXmlAsserter asserter = new FlatXmlAsserter(this, H2Connections.MAIN_SCHEMA);
		
		String query = "select distinct QUALITAET from FZA.AFZ_DATENQUELLE order by 1 asc";
		String table = "FZA.AFZ_DATENQUELLE";
		String fileName = "FLAT_XML_ASSERTER_TESTQUERY_result.xml";
		
		asserter.assertQueryResultMatchesXmlFile(query, table, fileName);
	}
	
//	@Test
	public void testAsserterWithDbConnector() throws Exception {
		DbConnector connector = new DbConnector(H2Connections.MAIN_SCHEMA);
		// setup
		FlatXmlImporter importer = new FlatXmlImporter(this, connector);
		importer.loadData("FZA.AFZ_DATENQUELLE_testdata.xml");

		// action
		FlatXmlAsserter asserter = new FlatXmlAsserter(this, connector);
		
		String query = "select distinct QUALITAET from FZA.AFZ_DATENQUELLE order by 1 asc";
		String table = "FZA.AFZ_DATENQUELLE";
		String fileName = "FLAT_XML_ASSERTER_TESTQUERY_result.xml";
		
		asserter.assertQueryResultMatchesXmlFile(query, table, fileName);
	}
}