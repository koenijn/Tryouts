/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.mvnliquibase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mark
 */
@Entity(name="persons")
public class Person {

    @Id
    @Column(name="prs_id", insertable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prsId;
    
    @Column(name="name", nullable=false)
    private String name;

    /**
     * @return the prsId
     */
    public Long getPrsId() {
        return prsId;
    }

    /**
     * @param prsId the prsId to set
     */
    public void setPrsId(Long prsId) {
        this.prsId = prsId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.prsId != other.prsId) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        
        int prime = 13;
        
        hash += name != null ? name.hashCode() : prime;
        hash += prsId != null ? prsId.hashCode() : prime;
        hash *= prime;
        
        return hash;
    }

}
