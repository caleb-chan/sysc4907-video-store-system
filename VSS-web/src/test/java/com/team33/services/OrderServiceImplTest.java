/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.Orders;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import com.team33.services.exception.InsufficientFundsException;
import com.team33.services.exception.PaymentException;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/service/service-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true) 
@Transactional
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderServiceImpl;
    private CreditCardValidator validator;

    public OrderServiceImplTest() {
        validator = new CreditCardValidator();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        if (orderServiceImpl != null) {
            orderServiceImpl.setCreditCardValidator(validator);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isActivated method, of class OrderServiceImpl.
     */
    @Test
    public void testIsActivated_ValidId() {
        try {
            assertTrue(this.orderServiceImpl.isActivated(1));
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    @Test
    public void testIsActivated_ValidId_NotActivated() {
        try {
            assertFalse(this.orderServiceImpl.isActivated(2));
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    public void testIsActivated_NegId() {
        try {
            assertTrue(this.orderServiceImpl.isActivated(-1));
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    public void testIsActivated_InvalidId() {
        try {
            assertTrue(this.orderServiceImpl.isActivated(9999));
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    /**
     * Test of addPurchase method, of class OrderServiceImpl.
     */
    @Test
    @Rollback(true)
    public void testAddPurchase_ValidVideoId() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addPurchase(1, order, 1);
        } catch (DataAccessException ex) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddPurchase_NullVideoId() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addPurchase(null, order, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddPurchase_NegVideoId() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addPurchase(-1, order, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddPurchase_InvalidVideoId() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addPurchase(9999, order, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testAddPurchase_NullOrder() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addPurchase(1, null, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testAddPurchase_NegOrderId() {
        Orders order = new Orders(-1, 1);
        try {
            this.orderServiceImpl.addPurchase(1, order, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testAddPurchase_InvalidOrderId() {
        Orders order = new Orders(9999, 1);
        try {
            this.orderServiceImpl.addPurchase(1, order, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testAddPurchase_InvalidAccountId() {
        Orders order = new Orders(1, 9999);
        try {
            this.orderServiceImpl.addPurchase(1, order, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testAddPurchase_NegAccountId() {
        Orders order = new Orders(1, -1);
        try {
            this.orderServiceImpl.addPurchase(1, order, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testAddPurchase_NegUUId() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addPurchase(1, order, -1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testAddPurchase_InvalidUUId() {
        Orders order = new Orders(1, -1);
        try {
            this.orderServiceImpl.addPurchase(1, order, 9999);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testAddPurchase_ValidVideoId_NotActivated() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addPurchase(1, order, 2);
            fail("AccountNotActivatedException should be thrown");
        } catch (DataAccessException ex) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException ex) {
        }
    }
    
    

    /**
     * Test of addRental method, of class OrderServiceImpl.
     */
    @Test
    @Rollback(true)
    public void testAddRental_ValidVideoId() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addRental(1, order, 1, new Date(System.currentTimeMillis()));
        } catch (DataAccessException ex) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_NullVideoId() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addRental(null, order, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_NegVideoId() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addRental(-1, order, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_InvalidVideoId() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addRental(9999, order, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should not be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_NullOrder() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addRental(1, null, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should not be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_NegOrderId() {
        Orders order = new Orders(-1, 1);
        try {
            this.orderServiceImpl.addRental(1, order, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should not be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_InvalidOrderId() {
        Orders order = new Orders(9999, 1);
        try {
            this.orderServiceImpl.addRental(1, order, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should not be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_InvalidAccountId() {
        Orders order = new Orders(1, 9999);
        try {
            this.orderServiceImpl.addRental(1, order, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should not be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_NegAccountId() {
        Orders order = new Orders(1, -1);
        try {
            this.orderServiceImpl.addRental(1, order, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should not be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_NegUUId() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addRental(1, order, -1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should not be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_InvalidUUId() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addRental(1, order, 9999, new Date(System.currentTimeMillis()));
            fail("DataAccessException should not be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_ValidVideoId_NotActivated() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addRental(1, order, 2, new Date(System.currentTimeMillis()));
            fail("DataAccessException should not be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_NullDate() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addRental(1, order, 1, null);
            fail("DataAccessException should not be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_InvalidDate() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addRental(1, order, 2, new Date(System.currentTimeMillis() - 631170000000L));
            fail("DataAccessException should not be thrown");
        } catch (DataAccessException ex) {
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_PastDateDate() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addRental(1, order, 2, new Date(System.currentTimeMillis() - 1000L));
        } catch (DataAccessException ex) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testAddRental_FutureDate() {
        Orders order = new Orders(1, 1);
        try {
            this.orderServiceImpl.addRental(1, order, 2, new Date(System.currentTimeMillis() + 1000L));
        } catch (DataAccessException ex) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    /**
     * Test of confirmPayment method, of class OrderServiceImpl.
     */
    @Test
    @Rollback(true)
    public void testConfirmPayment_ValidOrderId() {
        Orders testOrder = new Orders(1, 1);
        try {
            this.orderServiceImpl.confirmPayment(testOrder, 1, 459, 100);
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (PaymentException ex) {
            fail("PaymentException should not be thrown");
        } catch (InsufficientFundsException ex) {
            fail("InsufficientFundsException should not be thrown");
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testConfirmPayment_ValidOrderId_NotActivated() {
        Orders testOrder = new Orders(1, 2);
        try {
            this.orderServiceImpl.confirmPayment(testOrder, 2, 459, 100);
            fail("AccountNotActivatedException should not be thrown");
        } catch (AccountNotActivatedException ex) {
        } catch (PaymentException ex) {
            fail("PaymentException should not be thrown");
        } catch (InsufficientFundsException ex) {
            fail("InsufficientFundsException should not be thrown");
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testConfirmPayment_NegOrderId() {
        Orders testOrder = new Orders(-1, 1);
        try {
            this.orderServiceImpl.confirmPayment(testOrder, 1, 459, 100);
            fail("DataAccessException should be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (PaymentException ex) {
            fail("PaymentException should not be thrown");
        } catch (InsufficientFundsException ex) {
            fail("InsufficientFundsException should not be thrown");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    public void testConfirmPayment_InvalidOrderId() {
        Orders testOrder = new Orders(9999, 1);
        try {
            this.orderServiceImpl.confirmPayment(testOrder, 1, 459, 100);
            fail("DataAccessException should be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (PaymentException ex) {
            fail("PaymentException should not be thrown");
        } catch (InsufficientFundsException ex) {
            fail("InsufficientFundsException should not be thrown");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    public void testConfirmPayment_InvalidOrderAccountId() {
        Orders testOrder = new Orders(1, 9999);
        try {
            this.orderServiceImpl.confirmPayment(testOrder, 1, 459, 100);
            fail("DataAccessException should be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (PaymentException ex) {
            fail("PaymentException should not be thrown");
        } catch (InsufficientFundsException ex) {
            fail("InsufficientFundsException should not be thrown");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    public void testConfirmPayment_NegOrderAccountId() {
        Orders testOrder = new Orders(1, -1);
        try {
            this.orderServiceImpl.confirmPayment(testOrder, 1, 459, 100);
            fail("DataAccessException should be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (PaymentException ex) {
            fail("PaymentException should not be thrown");
        } catch (InsufficientFundsException ex) {
            fail("InsufficientFundsException should not be thrown");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    public void testConfirmPayment_NegUUId() {
        Orders testOrder = new Orders(1, 1);
        try {
            this.orderServiceImpl.confirmPayment(testOrder, -1, 459, 100);
            fail("DataAccessException should be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (PaymentException ex) {
            fail("PaymentException should not be thrown");
        } catch (InsufficientFundsException ex) {
            fail("InsufficientFundsException should not be thrown");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    public void testConfirmPayment_InvalidUUId() {
        Orders testOrder = new Orders(1, 1);
        try {
            this.orderServiceImpl.confirmPayment(testOrder, 9999, 459, 100);
            fail("DataAccessException should be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (PaymentException ex) {
            fail("PaymentException should not be thrown");
        } catch (InsufficientFundsException ex) {
            fail("InsufficientFundsException should not be thrown");
        } catch (DataAccessException e) {
        }
    }

    @Test
    @Rollback(true)
    public void testConfirmPayment_InvalidValidationNum() {
        Orders testOrder = new Orders(-1, 1);
        try {
            this.orderServiceImpl.confirmPayment(testOrder, 1, 1, 100);
            fail("PaymentException should be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (PaymentException ex) {
        } catch (InsufficientFundsException ex) {
            fail("InsufficientFundsException should not be thrown");
        } catch (DataAccessException e) {
            fail("DataAccessException should be not thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testConfirmPayment_NegTotalCost() {
        Orders testOrder = new Orders(-1, 1);
        try {
            this.orderServiceImpl.confirmPayment(testOrder, 1, 1, -1);
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (PaymentException ex) {
            fail("PaymentException should be not thrown");
        } catch (InsufficientFundsException ex) {
            fail("InsufficientFundsException should not be thrown");
        } catch (DataAccessException e) {
            fail("DataAccessException should be not thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testConfirmPayment_LOCCost() {
        Orders testOrder = new Orders(-1, 1);
        try {
            this.orderServiceImpl.confirmPayment(testOrder, 1, 1, 1000000);
            fail("InsufficientFundsException should be thrown");
        } catch (AccountNotActivatedException ex) {
            fail("AccountNotActivatedException should not be thrown");
        } catch (PaymentException ex) {
            fail("PaymentException should be not thrown");
        } catch (InsufficientFundsException ex) {
        } catch (DataAccessException e) {
            fail("DataAccessException should be not thrown");
        }
    }

    /**
     * Test of getOrder method, of class OrderServiceImpl.
     */
    @Test
    public void testGetOrder_ValidAccountId() throws Exception {
        Orders order = null;
        try {
            order = this.orderServiceImpl.getOrder(1, 1);
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
        assertNotNull(order);
        assertEquals(order.getOrdersPK().getAccountid(), 1);
        assertEquals(order.getOrdersPK().getId(), 1);
    }

    @Test
    public void testGetOrder_ValidAccountId_NotActivated() throws Exception {
        Orders order = null;
        try {
            order = this.orderServiceImpl.getOrder(1, 2);
            fail("AccountNotActivatedException should be thrown");
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException e) {
        }
    }

    @Test
    public void testGetOrder_InvalidAccountId() throws Exception {
        Orders order = null;
        try {
            order = this.orderServiceImpl.getOrder(1, 9999);
            fail("AccountNotActivatedException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    public void testGetOrder_NegAccountId() throws Exception {
        Orders order = null;
        try {
            order = this.orderServiceImpl.getOrder(1, -1);
            fail("AccountNotActivatedException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    public void testGetOrder_InvalidOrderId() throws Exception {
        Orders order = null;
        try {
            order = this.orderServiceImpl.getOrder(9999, 1);
            fail("AccountNotActivatedException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    public void testGetOrder_NegOrderId() throws Exception {
        Orders order = null;
        try {
            order = this.orderServiceImpl.getOrder(-1, 1);
            fail("AccountNotActivatedException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    public void testGetOrder_NullOrderId() throws Exception {
        Orders order = null;
        try {
            order = this.orderServiceImpl.getOrder(null, 1);
            fail("AccountNotActivatedException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    /**
     * Test of getOrders method, of class OrderServiceImpl.
     */
    @Test
    public void testGetOrders_ValidId() throws Exception {
        List<Orders> orders = null;
        try {
            orders = this.orderServiceImpl.getOrders(1);
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    public void testGetOrders_ValidId_NotActivated() throws Exception {
        List<Orders> orders = null;
        try {
            orders = this.orderServiceImpl.getOrders(2);
            fail("AccountNotActivatedException should be thrown");
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    public void testGetOrders_InvalidId() throws Exception {
        List<Orders> orders = null;
        try {
            orders = this.orderServiceImpl.getOrders(9999);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    public void testGetOrders_NegId() throws Exception {
        List<Orders> orders = null;
        try {
            orders = this.orderServiceImpl.getOrders(-1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    /**
     * Test of removeOrder method, of class OrderServiceImpl.
     */
    @Test
    @Rollback(true)
    public void testRemoveOrder_ValidOrderId() throws Exception {
        try {
            this.orderServiceImpl.removeOrder(1, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_ValidOrderId_NotActivated() throws Exception {
        try {
            this.orderServiceImpl.removeOrder(1, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException e) {
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_NullOrderId() throws Exception {
        try {
            this.orderServiceImpl.removeOrder(null, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_NegOrderId() throws Exception {
        try {
            this.orderServiceImpl.removeOrder(-1, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_InvalidOrderId() throws Exception {
        try {
            this.orderServiceImpl.removeOrder(9999, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_InavlidUUID() throws Exception {
        try {
            this.orderServiceImpl.removeOrder(1, 9999);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveOrder_NegUUID() throws Exception {
        try {
            this.orderServiceImpl.removeOrder(1, -1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    /**
     * Test of removePurchase method, of class OrderServiceImpl.
     */
    @Test
    @Rollback(true)
    public void testRemovePurchase_ValidVideoId() {
        try {
            this.orderServiceImpl.removePurchase(1, 1, 1);
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemovePurchase_NullVideoId() {
        try {
            this.orderServiceImpl.removePurchase(null, 1, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemovePurchase_NegVideoId() {
        try {
            this.orderServiceImpl.removePurchase(-1, 1, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemovePurchase_InvalidVideoId() {
        try {
            this.orderServiceImpl.removePurchase(9999, 1, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemovePurchase_NullOrderId() {
        try {
            this.orderServiceImpl.removePurchase(1, null, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemovePurchase_NegOrderId() {
        try {
            this.orderServiceImpl.removePurchase(1, -1, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemovePurchase_InvalidOrderId() {
        try {
            this.orderServiceImpl.removePurchase(1, 9999, 1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemovePurchase_NegAccountId() {
        try {
            this.orderServiceImpl.removePurchase(1, 1, -1);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemovePurchase_InvalidAccountId() {
        try {
            this.orderServiceImpl.removePurchase(1, 1, 9999);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    /**
     * Test of removeRental method, of class OrderServiceImpl.
     */
    @Test
    @Rollback(true)
    public void testRemoveRental_ValidVideoId() {
        try {
            this.orderServiceImpl.removeRental(1, 1, 1, new Date(System.currentTimeMillis()));
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_ValidVideoId_NotActivated() {
        try {
            this.orderServiceImpl.removeRental(1, 1, 1, new Date(System.currentTimeMillis()));
        } catch (DataAccessException e) {
            fail("DataAccessException should not be thrown");
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_NullVideoId() {
        try {
            this.orderServiceImpl.removeRental(null, 1, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_NegVideoId() {
        try {
            this.orderServiceImpl.removeRental(-1, 1, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_InvalidVideoId() {
        try {
            this.orderServiceImpl.removeRental(9999, 1, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_NullOrderId() {
        try {
            this.orderServiceImpl.removeRental(1, null, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_NegOrderId() {
        try {
            this.orderServiceImpl.removeRental(1, -1, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_InvalidOrderId() {
        try {
            this.orderServiceImpl.removeRental(1, 9999, 1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_NegAccountId() {
        try {
            this.orderServiceImpl.removeRental(1, 1, -1, new Date(System.currentTimeMillis()));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_InvalidAccountId() {
        try {
            this.orderServiceImpl.removeRental(1, 1, 9999, new Date(System.currentTimeMillis()));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_NullDate() {
        try {
            this.orderServiceImpl.removeRental(1, 1, 1, null);
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_InvalidDate() {
        try {
            this.orderServiceImpl.removeRental(1, 1, 1, new Date(System.currentTimeMillis() - 631170000000L));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_PastDate() {
        try {
            this.orderServiceImpl.removeRental(1, 1, 1, new Date(System.currentTimeMillis() - 1000L));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    @Test
    @Rollback(true)
    public void testRemoveRental_Future() {
        try {
            this.orderServiceImpl.removeRental(1, 1, 1, new Date(System.currentTimeMillis() + 1000L));
            fail("DataAccessException should be thrown");
        } catch (DataAccessException e) {
        } catch (AccountNotActivatedException e) {
            fail("AccountNotActivatedException should not be thrown");
        }
    }
}
