/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.services.BrowseService;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Caleb
 */
public class ShoppingCartControllerTest {
    
    public ShoppingCartControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of viewCart method, of class ShoppingCartController.
     */
    @Test
    public void testViewCart() {
        System.out.println("viewCart");
        Map<String, Object> map = null;
        HttpSession session = null;
        ShoppingCartController instance = new ShoppingCartController();
        String expResult = "";
        String result = instance.viewCart(map, session);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePurchaseOrder method, of class ShoppingCartController.
     */
    @Test
    public void testDeletePurchaseOrder() {
        System.out.println("deletePurchaseOrder");
        String videoId = "";
        HttpSession session = null;
        ShoppingCartController instance = new ShoppingCartController();
        String expResult = "";
        String result = instance.deletePurchaseOrder(videoId, session);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRentalOrder method, of class ShoppingCartController.
     */
    @Test
    public void testDeleteRentalOrder() {
        System.out.println("deleteRentalOrder");
        String videoId = "";
        HttpSession session = null;
        ShoppingCartController instance = new ShoppingCartController();
        String expResult = "";
        String result = instance.deleteRentalOrder(videoId, session);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPurchaseToCart method, of class ShoppingCartController.
     */
    @Test
    public void testAddPurchaseToCart() {
        System.out.println("addPurchaseToCart");
        String videoId = "";
        HttpSession session = null;
        ShoppingCartController instance = new ShoppingCartController();
        String expResult = "";
        String result = instance.addPurchaseToCart(videoId, session);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addrentalToCart method, of class ShoppingCartController.
     */
    @Test
    public void testAddrentalToCart() {
        System.out.println("addrentalToCart");
        String videoId = "";
        HttpSession session = null;
        ShoppingCartController instance = new ShoppingCartController();
        String expResult = "";
        String result = instance.addrentalToCart(videoId, session);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
