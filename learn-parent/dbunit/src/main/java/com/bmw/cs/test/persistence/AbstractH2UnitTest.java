//______________________________________________________________________________
//
// Project: fza
//    File: $HeadURL: https://lpintrae.muc:4756/svn/fza/code/trunk/build/fza/fza/fza-common-test/src/main/java/com/bmw/cs/fza/persistence/AbstractDAOWithoutEMTest.java $
// Version: $Id: AbstractH2UnitTest.java 4190 2016-01-13 11:07:06Z qx72871 $
//______________________________________________________________________________
//
//    Created by: Stefan Knaus, stefan.knaus@sulzer.de
// Creation date: 02.12.2015
//    Changed by: $Author: qx72871 $
//   Change date: $Date:: 2016-01-13 #$
//______________________________________________________________________________
//
// Copyright: (C) BMW AG 2015, all rights reserved
//______________________________________________________________________________
package com.bmw.cs.test.persistence;

import com.bmw.cs.test.dbunit.connect.ConnectionInfo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * <!-- ==================================================================== -->
 * Access to Database with Entity Manager
 *
 * @author <a href="mailto:stefan.knaus@sulzer.de">Stefan Knaus</a> / <a href="http://www.sulzer.de"
 *         target="_blank">Sulzer GmbH</a>
 * @version $Revision: 4190 $ - $Author: qx72871 $ - $Date:: 2016-01-13 #$
 * @since Release 2.30.0
 */

public abstract class AbstractH2UnitTest {

    private static final Logger LOG = LogManager.getLogger(AbstractH2UnitTest.class);
    protected Connection connection;

    @Before
    public void setupH2Database() throws Exception {
        connection = getJDBCConnection(getConnectionInfo());
        for (String ddlFile : getDDLFileNames()) {
            executeSQLScript(ddlFile);
        }
    }

    protected abstract ConnectionInfo getConnectionInfo();

    protected abstract List<String> getDDLFileNames();

    protected void executeSQLScript(String ddlFile) throws SQLException, IOException {
        final URL fullFileURL = this.getClass().getResource(ddlFile);
        LOG.info("executing sql script: " + fullFileURL);

        try (InputStream sqlInputStream = this.getClass().getResourceAsStream(ddlFile)) {
            try (Reader reader = new InputStreamReader(sqlInputStream, "UTF-8");
                 ResultSet rs = RunScript.execute(connection, reader);) {
            }
        }
    }

    private Connection getJDBCConnection(final ConnectionInfo pDbConnInfo) throws SQLException {
        return DriverManager.getConnection(pDbConnInfo.getJdbcURL(), pDbConnInfo.getJdbcUser(),
                pDbConnInfo.getJdbcPassword());
    }

    @After
    public void shutDownH2Database() throws SQLException {
        if (this.connection != null) {
            try (Statement stmt = this.connection.createStatement()) {
                stmt.executeUpdate("SHUTDOWN IMMEDIATELY");
            }
            try {
                this.connection.close();
            } catch (Exception ex) {
                // ignore
            }
        }
    }
}
