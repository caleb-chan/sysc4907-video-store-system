/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.mock.web.MockHttpSession;

/**
 *
 * @author Caleb
 */
public class LogoutControllerTest {
    
    public LogoutControllerTest() {
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
     * Test of logout method, of class LogoutController.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        HttpSession session = new MockHttpSession();
        session.setAttribute(LoginController.ACCOUNT_ATTRIBUTE, "");
        session.setAttribute(ShoppingCartController.SHOPPING_CART_ATTRIBUTE_NAME, "");
        LogoutController instance = new LogoutController();
        String expResult = "redirect:/";
        String result = instance.logout(session);
        assertEquals(expResult, result);
        assertEquals(session.getAttribute(LoginController.ACCOUNT_ATTRIBUTE), null);
        assertEquals(session.getAttribute(ShoppingCartController.SHOPPING_CART_ATTRIBUTE_NAME), null);
    }
}
