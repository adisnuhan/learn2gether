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
import com.bmw.cs.test.persistence.AbstractH2UnitTest;

import java.util.Arrays;
import java.util.List;

/**
 * <!-- ==================================================================== -->
 *
 * @author <a href="mailto:stefan.knaus@sulzer.de">Stefan Knaus</a>
 *         / <a href="http://www.sulzer.de" target="_blank">Sulzer GmbH</a>
 */
public class TableViewerTest extends AbstractH2UnitTest {

    @Override
    protected ConnectionInfo getConnectionInfo() {
        return H2Connections.MAIN_SCHEMA;
    }

    @Override
    protected List<String> getDDLFileNames() {
        return Arrays.asList(new String[] { "/ddl/create_table_AFZ_DATENQUELLE.sql" });
    }

//    @Test
    public void testPrintQuery() throws Exception {
        // setup
        FlatXmlImporter imp = new FlatXmlImporter(this, H2Connections.MAIN_SCHEMA);
        imp.loadData("FZA.AFZ_DATENQUELLE_testdata.xml");

        // action
        new TableViewer(H2Connections.MAIN_SCHEMA).printQuery("FZA.AFZ_DATENQUELLE", "SELECT * FROM FZA.AFZ_DATENQUELLE");
        new HtmlTableExporter(H2Connections.MAIN_SCHEMA).exportQuery("FZA.AFZ_DATENQUELLE", "SELECT * FROM FZA" +
                ".AFZ_DATENQUELLE", "target/test_export_query" +
                ".html");
    }

//    @Test
    public void testPrintTable() throws Exception {
        // setup
        FlatXmlImporter imp = new FlatXmlImporter(this, H2Connections.MAIN_SCHEMA);
        imp.loadData("FZA.AFZ_DATENQUELLE_testdata.xml");

        // action
        new TableViewer(H2Connections.MAIN_SCHEMA).printTable("FZA.AFZ_DATENQUELLE");
        new HtmlTableExporter(H2Connections.MAIN_SCHEMA).exportTable("FZA.AFZ_DATENQUELLE", "target/test_export_table" +
                ".html");
    }

//    @Test
    public void testPrintCompleteDatabase() throws Exception {
        // setup
        FlatXmlImporter imp = new FlatXmlImporter(this, H2Connections.MAIN_SCHEMA);
        imp.loadData("FZA.AFZ_DATENQUELLE_testdata.xml");

        // action
        new TableViewer(H2Connections.MAIN_SCHEMA).printSchema();
        new HtmlTableExporter(H2Connections.MAIN_SCHEMA).exportSchema("target/test_export_schema.html");
    }
    
//    @Test
    public void testPrintDatabaseWithDbConnector() throws Exception {
        // setup
        FlatXmlImporter imp = new FlatXmlImporter(this, new DbConnector(H2Connections.MAIN_SCHEMA));
        imp.loadData("FZA.AFZ_DATENQUELLE_testdata.xml");

        // action
        new TableViewer(H2Connections.MAIN_SCHEMA).printSchema();
    }
}