/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.Account;
import com.team33.services.AccountService;
import com.team33.services.exception.RegistrationException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/controller/controller-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true) 
@Transactional
public class RegisterAccountControllerTest {
    
    @Autowired
    private RegisterAccountController controller;
    private RedirectAttributes redirect;
    
    public RegisterAccountControllerTest() {
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
     * Test of register method, of class RegisterAccountController.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        String expResult = "registerAccountView";
        String result = controller.register();
        assertEquals(expResult, result);
    }

    /**
     * Test of handleRegistration method, of class RegisterAccountController.
     */
    @Test
    public void testHandleRegistration_ValidInput() throws Exception {
        System.out.println("handleRegistration");
        String username = "Caleb";
        String password = "1234";
        HttpSession session = new MockHttpSession();
        String expResult = "registerSuccess";
        String result = controller.handleRegistration(username, password, redirect, session);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testHandleRegistration_BlankUsrInput() throws Exception {
        System.out.println("handleRegistration");
        String username = "";
        String password = "1234";
        HttpSession session = new MockHttpSession();
        String expResult = "redirect:/registerAccountView.htm";
        String result = controller.handleRegistration(username, password, redirect, session);
        assertEquals(expResult, result);
        assertTrue(redirect.containsAttribute("exception"));
        assertTrue(redirect.getFlashAttributes().get("exception") instanceof RegistrationException);
    }
    
    @Test
    public void testHandleRegistration_BlankPwdInput() throws Exception {
        System.out.println("handleRegistration");
        String username = "Caleb";
        String password = "";
        HttpSession session = new MockHttpSession();
        String expResult = "redirect:/registerAccountView.htm";
        String result = controller.handleRegistration(username, password, redirect, session);
        assertEquals(expResult, result);
        assertTrue(redirect.containsAttribute("exception"));
        assertTrue(redirect.getFlashAttributes().get("exception") instanceof RegistrationException);
    }
    @Test
    public void testHandleRegistration_NullUsrInput() throws Exception {
        System.out.println("handleRegistration");
        String username = null;
        String password = "1234";
        HttpSession session = new MockHttpSession();
        String expResult = "redirect:/registerAccountView.htm";
        String result = controller.handleRegistration(username, password, redirect, session);
        assertEquals(expResult, result);
        assertTrue(redirect.containsAttribute("exception"));
        assertTrue(redirect.getFlashAttributes().get("exception") instanceof RegistrationException);
    }
    
    @Test
    public void testHandleRegistration_NullPwdInput() throws Exception {
        System.out.println("handleRegistration");
        String username = "Caleb";
        String password = null;
        HttpSession session = new MockHttpSession();
        String expResult = "redirect:/registerAccountView.htm";
        String result = controller.handleRegistration(username, password, redirect, session);
        assertEquals(expResult, result);
        assertTrue(redirect.containsAttribute("exception"));
        assertTrue(redirect.getFlashAttributes().get("exception") instanceof RegistrationException);
    }

    /**
     * Test of onSubmit method, of class RegisterAccountController.
     */
    @Test
    public void testOnSubmit() throws Exception {
        System.out.println("onSubmit");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        Object command = new Account(1);
        BindException errors = null;
        RegisterAccountController instance = new RegisterAccountController();
        ModelAndView result = instance.onSubmit(request, response, command, errors);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
