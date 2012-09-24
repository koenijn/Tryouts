/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jbehave.application.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author mark
 */
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="CMR_SEQ")
    @SequenceGenerator(name="CMR_SEQ", sequenceName="CMR_SEQ")
    private Long customerNumber;
    @NotNull
    private String companyName;
    @NotNull
    @Valid
    @Embedded
    private Address contactAddress;

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getContactAddress() {
        if (contactAddress != null) {
            return contactAddress.makeCopy();
        }
        else {
            return contactAddress;
        }
    }

    public void setContactAddress(Address contactAddress) {
        if (contactAddress != null) {
            this.contactAddress = contactAddress.makeCopy();
        }
        else {
            this.contactAddress = null;
        }
        
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Customer == false) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Customer other = (Customer) obj;
        return new EqualsBuilder().append(customerNumber, other.customerNumber).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(5, 97).append(this.customerNumber).toHashCode();

    }
}
