/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.jbehave.application.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mark
 */
@Embeddable
public class Address implements Serializable {
    
    @NotNull
    private String country;
    @NotNull
    private String postalCode;
    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private String houseNumber;

    public Address makeCopy(){
        Address copy = new Address();
        copy.country = this.country;
        copy.postalCode = this.postalCode;
        copy.city = this.city;
        copy.street = this.street;
        copy.houseNumber = this.houseNumber;
        return copy;
    }
    
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if ((this.country == null) ? (other.country != null) : !this.country.equals(other.country)) {
            return false;
        }
        if ((this.postalCode == null) ? (other.postalCode != null) : !this.postalCode.equals(other.postalCode)) {
            return false;
        }
        if ((this.city == null) ? (other.city != null) : !this.city.equals(other.city)) {
            return false;
        }
        if ((this.street == null) ? (other.street != null) : !this.street.equals(other.street)) {
            return false;
        }
        if ((this.houseNumber == null) ? (other.houseNumber != null) : !this.houseNumber.equals(other.houseNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.country != null ? this.country.hashCode() : 0);
        hash = 97 * hash + (this.postalCode != null ? this.postalCode.hashCode() : 0);
        hash = 97 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 97 * hash + (this.street != null ? this.street.hashCode() : 0);
        hash = 97 * hash + (this.houseNumber != null ? this.houseNumber.hashCode() : 0);
        return hash;
    }
    
}
