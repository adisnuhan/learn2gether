//______________________________________________________________________________
//
// Project: learn-parent
//    File: $HeadURL: $
// Version: $Id: $
//______________________________________________________________________________
//
//    Created by: Adis Nuhanovic, adis.nuhanovic@sulzer.de
// Creation date: 12.02.2017
//    Changed by: $Author: $
//   Change date: $Date::            #$
//______________________________________________________________________________
//
// Copyright: (C) BMW AG 2017, all rights reserved
//______________________________________________________________________________
package com.learntogether.user.boundary;

import com.learntogether.user.entity.CustomerBE;
import com.learntogether.user.test.AbstractDAOTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * <!-- ==================================================================== -->
 * Testclass for {@link UserSessionBean}
 *
 * @author <a href="mailto:adis.nuhanovic@sulzer.de">Adis Nuhanovic</a> /
 *         <a href="http://www.sulzer.de"
 *         target="_blank">Sulzer GmbH</a>
 * @since Release 1.0
 */
// @RunWith(MockitoJUnitRunner.class)
// @RunWith(Parameterized.class)
// @NotThreadSafe
public class TestUserSessionBean  {

	private UserSessionBean cut;
    private EntityManager em;
	/**
	 * Sets up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(AbstractDAOTest.MYSQL_PERSISTENCE_UNIT);
		em = emf.createEntityManager();
		cut = new UserSessionBean(em);
	}
    

	/**
	 * Test get customer dto.
	 */
	@Test
	public void testGetCustomerByID() {
		CustomerBE customerBE = cut.getCustomersById(1L);
		Assert.assertEquals("The id must entity equal to ", Long.valueOf(1), customerBE.getId());
		Assert.assertEquals("The name must entity equal to ", "nuhanovicc", customerBE.getName());
	}

    @Test
    public void testGetCustomers() {
        List<CustomerBE> customerBEs = cut.getCustomers();
        Assert.assertEquals("The list must not be empty ", true, !customerBEs.isEmpty());
    }

    

}