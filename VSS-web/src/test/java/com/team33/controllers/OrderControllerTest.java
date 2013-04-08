/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.form.OrderRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/controller/controller-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true) 
@Transactional
public class OrderControllerTest {
    @Autowired
    private OrderController controller;
    private RedirectAttributes redirect;
    
    public OrderControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        redirect = new RedirectAttributes() {
            private HashMap<String, Object> map = new HashMap<String, Object>();

            @Override
            public RedirectAttributes addAttribute(String string, Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public RedirectAttributes addAttribute(Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public RedirectAttributes addAllAttributes(Collection<?> clctn) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public RedirectAttributes mergeAttributes(Map<String, ?> map) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public RedirectAttributes addFlashAttribute(String string, Object o) {
                map.put(string, o);
                return this;
            }

            @Override
            public RedirectAttributes addFlashAttribute(Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Map<String, ?> getFlashAttributes() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean containsAttribute(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Map<String, Object> asMap() {
                return this.map;
            }
        };
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createOrder method, of class OrderController.
     */
    @Test
    @Rollback(true)
    public void testCreateOrder() {
        System.out.println("createOrder");
        String referer = "";
        OrderRequest orderRequest = null;
        BindingResult result_2 = null;
        HttpSession session = null;
        RedirectAttributes redirectAttributes = null;
        OrderController instance = new OrderController();
        String expResult = "";
        String result = instance.createOrder(referer, orderRequest, result_2, session, redirectAttributes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newOrder method, of class OrderController.
     */
    @Test
    @Rollback(true)
    public void testNewOrder() {
        System.out.println("newOrder");
        String referer = "";
        Map<String, Object> model = null;
        HttpSession session = null;
        OrderController instance = new OrderController();
        String expResult = "";
        String result = instance.newOrder(referer, model, session);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
