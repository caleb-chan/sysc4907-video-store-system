/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Caleb
 */
public class IndexControllerTest {
    
    public IndexControllerTest() {
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
     * Test of indexPage method, of class IndexController.
     */
    @Test
    public void testIndexPage() {
        System.out.println("indexPage");
        String errorMessage = "";
        Map<String, Object> model = new HashMap<String, Object>();
        IndexController instance = new IndexController();
        String expResult = "index";
        String result = instance.indexPage(errorMessage, model);
        assertEquals(expResult, result);
        assertTrue(!model.isEmpty());
        assertTrue(model.containsKey("errorMessage"));
        assertEquals(model.get("errorMessage"), "");
    }

    /**
     * Test of handleRequest method, of class IndexController.
     */
    @Test
    public void testHandleRequest() {
        System.out.println("handleRequest");
        RedirectAttributes redirect = null;
        HttpSession session = null;
        IndexController instance = new IndexController();
        String expResult = "redirect:" + redirect;
        String result = instance.handleRequest(redirect, session);
        assertEquals(expResult, result);
    }
}
