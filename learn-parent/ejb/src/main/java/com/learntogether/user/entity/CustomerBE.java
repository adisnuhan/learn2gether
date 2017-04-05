//______________________________________________________________________________
//
// Project: learn-parent
//    File: $HeadURL: $
// Version: $Id: $
//______________________________________________________________________________
//
//    Created by: Adis Nuhanovic, adis.nuhanovic@sulzer.de
// Creation date: 31.01.2017
//    Changed by: $Author: $
//   Change date: $Date::            #$
//______________________________________________________________________________
//
// Copyright: (C) BMW AG 2017, all rights reserved
//______________________________________________________________________________
package com.learntogether.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * <!-- ==================================================================== -->
 * Entity Bean for Customer
 *
 * @author <a href="mailto:adis.nuhanovic@sulzer.de">Adis Nuhanovic</a>
 *         / <a href="http://www.sulzer.de" target="_blank">Sulzer GmbH</a>
 * @version $Revision: $ - $Author: $ - $Date:: #$
 * @since Release 1.0
 */
@Entity
@Table(name = CustomerBE.TABLE)
@NamedQueries({@NamedQuery(name = CustomerBE.findByCustomerId, query = "SELECT c FROM " + CustomerBE.ENTITY + " c " +
        "WHERE c.id= :" + CustomerBE.PARAM_ID),
               @NamedQuery(name = CustomerBE.returnAllCustomers, query = "SELECT c FROM " + CustomerBE.ENTITY + " c "),})
public class CustomerBE implements Serializable {

    public static final String TABLE = "LEARN2GETHER.USER";
    public static final String ENTITY = "CustomerBE";

    public static final String findByCustomerId = ENTITY + ".findByCustomerId";
    public static final String returnAllCustomers = ENTITY + ".returnAllCustomers";
    public static final String PARAM_ID = "id";
    @Id
    private Long		id;
    @Column(name = "MYUSER")
	private String	name;

    @Column(name = "EMAIL")
    private String email;

    public CustomerBE() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long pId) {
        id = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String pName) {
        name = pName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String pEmail) {
        email = pEmail;
    }

    @Override
    public boolean equals(final Object pO) {
        if (this == pO) return true;
        if (pO == null || getClass() != pO.getClass()) return false;
        final CustomerBE that = (CustomerBE) pO;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomerBE{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
