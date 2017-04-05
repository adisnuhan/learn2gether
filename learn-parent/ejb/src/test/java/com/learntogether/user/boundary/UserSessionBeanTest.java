//______________________________________________________________________________
//
// Project: learn-parent
//    File: $HeadURL: $
// Version: $Id: $
//______________________________________________________________________________
//
//    Created by: Adis Nuhanovic, adis.nuhanovic@sulzer.de
// Creation date: 29.03.2017
//    Changed by: $Author: $
//   Change date: $Date::            #$
//______________________________________________________________________________
//
// Copyright: (C) BMW AG 2017, all rights reserved
//______________________________________________________________________________
package com.learntogether.user.boundary;


import com.bmw.cs.test.dbunit.FlatXmlImporter;
import com.bmw.cs.test.dbunit.TableViewer;
import com.bmw.cs.test.dbunit.connect.H2Connections;
import com.bmw.cs.test.dbunit.connect.MySQLConnections;
import com.learntogether.user.entity.CustomerBE;
import com.learntogether.user.test.AbstractDAOTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

// @RunWith(MockitoJUnitRunner.class)
// @RunWith(Parameterized.class)
public class UserSessionBeanTest extends AbstractDAOTest {

	private UserSessionBean cut;

	@Before
    public void setUp() throws Exception {
        super.setUp();
        cut = new UserSessionBean(em);
        FlatXmlImporter flatXmlImporter = new FlatXmlImporter(this, H2Connections.MAIN_SCHEMA);
        flatXmlImporter.loadData("UserSessionBean_testdata.xml");
    }

	@After
	public void tearDown() throws SQLException {
        super.tearDown();
	}

	@Test
	public void testGetCustomerDTO() {
		CustomerBE customerBE = cut.getCustomersById(2L);
		Assert.assertEquals("The id must entity equal to ", Long.valueOf(2), customerBE.getId());
		Assert.assertEquals("The name must entity equal to ", "Nuhanovic", customerBE.getName());
	}

	@Override
	protected String getPersistenceUnitName() {
		return AbstractDAOTest.H2_PERSISTENCE_UNIT;
	}

	@Override
	protected List<String> getSQLFileResourceNames() {
		return Arrays.asList(new String[] { "/ddl/create_tables.sql" });
	}


	//@Test
    public void smallTest()throws Exception{
        TableViewer viewer = new TableViewer(MySQLConnections.MAIN_SCHEMA);
        viewer.printQuery("USER", "select * from user");
    }
}