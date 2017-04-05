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

import com.bmw.cs.test.dbunit.connect.DbConnector;
import com.bmw.cs.test.dbunit.connect.MySQLConnections;

/**
 * <!-- ==================================================================== -->
 * Testclass for {@link FlatXmlExporter}
 *
 * @author <a href="mailto:stefan.knaus@sulzer.de">Stefan Knaus</a>
 *         / <a href="http://www.sulzer.de" target="_blank">Sulzer GmbH</a>
 */
public class FlatXmlExporterTest {

//    @Test
    public void testExportTableAsFlatXml_WithGivenFileName() throws Exception {
        FlatXmlExporter ex = new FlatXmlExporter(this, MySQLConnections.MAIN_SCHEMA);

        ex.exportSingleTable("FZA.ROL_REGEL_NOTIFICATION", "target/myExport.xml");
    }

//    @Test
    public void testExportTableAsFlatXml_WithDefaultFileName() throws Exception {
        FlatXmlExporter ex = new FlatXmlExporter(this, MySQLConnections.MAIN_SCHEMA);

        ex.exportSingleTable("FZA.AFZ_DATENQUELLE");
    }

//    @Test
    public void testExportQueryResultAsFlatXml_WithGivenFileName() throws Exception {
        FlatXmlExporter ex = new FlatXmlExporter(this, MySQLConnections.MAIN_SCHEMA);

        ex.exportQueryResult("FZA.ROL_REGEL_NOTIFICATION", "SELECT * FROM FZA.ROL_REGEL_NOTIFICATION WHERE REGEL_ID='1560'", "target/abc.xml");
    }

//    @Test
    public void testExportQueryResultAsFlatXml_WithDefaultFileName() throws Exception {
        FlatXmlExporter ex = new FlatXmlExporter(this, MySQLConnections.MAIN_SCHEMA);

        ex.exportQueryResult("FZA.ROL_REGEL_NOTIFICATION", "SELECT * FROM FZA.ROL_REGEL_NOTIFICATION WHERE REGEL_ID='1560'");
    }

//    @Test
    public void testExportQueryResultForTableAndDependents_WithGivenFileName() throws Exception {
        FlatXmlExporter ex = new FlatXmlExporter(this, MySQLConnections.MAIN_SCHEMA);

        ex.exportQueryResultForTableAndDependents("FZA.AFZ_FAHRZEUGZUSTAND", " WHERE FGNR7='B123456'",
                "target/query_table_with_dependents.xml");
    }

//    @Test
    public void testexportQueryResultForTableAndDependents_WithDefaultFileName() throws Exception {
        FlatXmlExporter ex = new FlatXmlExporter(this, MySQLConnections.MAIN_SCHEMA);

        ex.exportQueryResultForTableAndDependents("FZA.FZH_FZ_BEHANDL_EV", " WHERE EVENT_ID='13232026'");
    }
    
//    @Test
    public void testexportQueryWithDbConnector() throws Exception {
        FlatXmlExporter ex = new FlatXmlExporter(this, new DbConnector(MySQLConnections.MAIN_SCHEMA));

        ex.exportQueryResultForTableAndDependents("FZA.FZH_FZ_BEHANDL_EV", " WHERE EVENT_ID='13232026'");
    }
}