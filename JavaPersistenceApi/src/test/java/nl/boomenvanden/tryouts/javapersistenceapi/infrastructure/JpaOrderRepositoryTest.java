/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.javapersistenceapi.infrastructure;

import javax.ejb.embeddable.EJBContainer;
import nl.boomenvanden.tryouts.javapersistenceapi.application.OrderRepository;
import nl.boomenvanden.tryouts.javapersistenceapi.application.domain.Order;
import nl.boomenvanden.tryouts.javapersistenceapi.application.domain.OrderLine;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author mark
 */
public class JpaOrderRepositoryTest extends BaseJpaTestCase {
    OrderRepository orderRepository;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        orderRepository = new JpaOrderRepository(em);
    }

    @Test
    public void testSave_newOrder_idFilled() throws Exception {
        Order order = new Order();
        order.setReference("MyReference");
        
        em.getTransaction().begin();
        orderRepository.save(order);
        em.getTransaction().commit();
        
        assertNotNull("Order ID should not be null", order.getId());
        
        Order savedOrder = orderRepository.findByPk(order.getId());
        assertNotNull("The saved order should be found [" + order.getId() + "]", savedOrder);
    }

    @Test
    public void testSave_newOrderWithTwoNewOrderLines_allKeysFilledCorrectly() throws Exception {
        Order order = new Order();
        order.setReference("MyReference");
        order.addOrderLine("Hagelslag", 3L);
        order.addOrderLine("Aardbeienjam", 5L);
        
        em.getTransaction().begin();
        orderRepository.save(order);
        em.getTransaction().commit();
        
        assertNotNull("Order ID should not be null", order.getId());
        assertEquals(2, order.getOrderLines().size());
        
        System.out.println("Order [" + order.getId() + "]");
        for (OrderLine orderLine : order.getOrderLines()) {
            assertNotNull("Id of oderLine should not be null", orderLine.getId());
            System.out.println("- OrderLine [" + orderLine.getId() + "]");
        }
        
        Long orderId = order.getId();
        order = null;
        
        Order savedOrder = orderRepository.findByPk(orderId);
        assertNotNull("The saved order should be found [" + orderId + "]", savedOrder);
        assertEquals(2, savedOrder.getOrderLines().size());

        System.out.println("Order [" + savedOrder.getId() + "]");
        for (OrderLine orderLine : savedOrder.getOrderLines()) {
            assertNotNull("Id of oderLine should not be null", orderLine.getId());
            System.out.println("- OrderLine [" + orderLine.getId() + "]");
        }
        
    }

    @Test
    public void testRemoveOrderLine_existingOrderWithTwoOrderLines_orderWithOneOrderLine() throws Exception {
        Order order = new Order();
        order.setReference("MyReference");
        order.addOrderLine("Hagelslag", 3L);
        order.addOrderLine("Aardbeienjam", 5L);
        
        em.getTransaction().begin();
        orderRepository.save(order);
        em.getTransaction().commit();
        
        
        em.getTransaction().begin();
        order = orderRepository.findByPk(order.getId());
        order.deleteOrderLine(order.getOrderLines().get(0));
        em.getTransaction().commit();
        
        Long orderId = order.getId();
        order = null;
        
        Order savedOrder = orderRepository.findByPk(orderId);
        assertEquals(1, savedOrder.getOrderLines().size());
    }

}
