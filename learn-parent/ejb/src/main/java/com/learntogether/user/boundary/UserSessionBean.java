/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learntogether.user.boundary;

import com.learntogether.user.entity.CustomerBE;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Adis
 */
@Stateless
@Local(UserSessionBean.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserSessionBean {

	private static final Log	LOG	= LogFactory.getLog(UserSessionBean.class);

	@PersistenceContext(unitName = "learntogether-ejb")
	private EntityManager		em;

	public UserSessionBean(final EntityManager pEm) {
		em = pEm;
	}

	public UserSessionBean() {
	}

	public CustomerBE getCustomersById(final Long pId) {
		CustomerBE customerBE = new CustomerBE();
		try {
			TypedQuery<CustomerBE> query = em.createNamedQuery(CustomerBE.findByCustomerId, CustomerBE.class);
			query.setParameter(CustomerBE.PARAM_ID, pId);
			customerBE = query.getSingleResult();
		} catch (Exception pE) {
			LOG.info("Unable to get Customer by id " + pE.getMessage());
		}
		return customerBE;
	}

	public List<CustomerBE> getCustomers() {
		List<CustomerBE> customerBEs = new ArrayList<>();
		try {
			TypedQuery<CustomerBE> query = em.createNamedQuery(CustomerBE.returnAllCustomers, CustomerBE.class);
			customerBEs = query.getResultList();
		} catch (Exception pE) {
			pE.printStackTrace();
		}
		return customerBEs;
	}

	public void saveCustmer(final String pUser, final String pEmail) {
		CustomerBE customerBE = new CustomerBE();
		customerBE.setName("test");
		customerBE.setEmail("testEmail");
		em.persist(customerBE);
		em.getTransaction().commit();
	}
}
