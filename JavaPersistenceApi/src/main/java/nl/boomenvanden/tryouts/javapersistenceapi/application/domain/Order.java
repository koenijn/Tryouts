/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.javapersistenceapi.application.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author mark
 */
@Entity(name="orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String reference;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="order_id")
    //@JoinColumn(name="order_id", nullable=false) // <== this doesn't work somehow
    private List<OrderLine> orderLines;
    
    public Order() {
        orderLines = new LinkedList<OrderLine>();
    }
    
    public Long getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<OrderLine> getOrderLines() {
        return Collections.unmodifiableList(orderLines);
    }
    
    public void addOrderLine(String productName, Long amount) {
        OrderLine orderLine = new OrderLine();
        orderLine.setProductName(productName);
        orderLine.setAmount(amount);
        
        this.orderLines.add(orderLine);
    }
    
    public void deleteOrderLine(OrderLine orderLine) {
        this.orderLines.remove(orderLine);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
}
