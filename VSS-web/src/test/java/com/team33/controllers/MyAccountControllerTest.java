/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.services.AccountService;
import com.team33.services.VideoAccessService;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/controller/controller-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true) 
@Transactional
public class MyAccountControllerTest {
    
    @Autowired
    private MyAccountController controller;
    
    private RedirectAttributes redirect;
    
    public MyAccountControllerTest() {
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
     * Test of displayAccountInfo method, of class MyAccountController.
     */
    @Test
    public void testDisplayAccountInfo_NoToken() {
        System.out.println("displayAccountInfo");
        String referer = "";
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = new MockHttpSession();
        String expResult = "redirect:/registerAccountView.htm";
        try{
            String result = controller.displayAccountInfo(referer, redirect, map, session);
            assertEquals(expResult, result);
        }catch(Exception e){
            fail("Exception should not be thrown");
        }
    }
    
    @Test
    public void testDisplayAccountInfo_ValidToken() {
        System.out.println("displayAccountInfo");
        String referer = "";
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = new MockHttpSession();
        session.setAttribute(LoginController.ACCOUNT_ATTRIBUTE, new Integer(1));
        String expResult = "/myAccountView";
        try{
            String result = controller.displayAccountInfo(referer, redirect, map, session);
            assertEquals(expResult, result);
            assertEquals(map.get("Name"), "Test1");
            assertNull(map.get("Orders"));
        }catch(Exception e){
            fail("Exception should not be thrown");
        }
    }
    
    @Test
    public void testDisplayAccountInfo_InvalidToken() {
        System.out.println("displayAccountInfo");
        String referer = "";
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = new MockHttpSession();
        String expResult = "redirect:" + referer;
        session.setAttribute(LoginController.ACCOUNT_ATTRIBUTE, new Integer(9999));
        try{
            String result = controller.displayAccountInfo(referer, redirect, map, session);
            assertEquals(expResult, result);
            assertTrue(redirect.containsAttribute("errorMessage"));
            assertTrue(redirect.getFlashAttributes().get("errorMessage") instanceof DataAccessException);
        }catch(Exception e){
            fail("Exception should not be thrown");
        }
    }
    
    @Test
    public void testDisplayAccountInfo_InvalidToken_NotActivated() {
        System.out.println("displayAccountInfo");
        String referer = "";
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = new MockHttpSession();
        String expResult = "redirect:" + referer;
        session.setAttribute(LoginController.ACCOUNT_ATTRIBUTE, new Integer(2));
        try{
            String result = controller.displayAccountInfo(referer, redirect, map, session);
            assertEquals(expResult, result);
            assertTrue(redirect.containsAttribute("errorMessage"));
            assertTrue(redirect.getFlashAttributes().get("errorMessage") instanceof AccountNotActivatedException);
        }catch(Exception e){
            fail("Exception should not be thrown");
        }
    }
}
