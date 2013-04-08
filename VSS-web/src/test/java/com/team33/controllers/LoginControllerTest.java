/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.Account;
import com.team33.services.AccountService;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.AccountNotFoundException;
import com.team33.services.exception.AuthenticationException;
import com.team33.services.exception.LoginException;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.FlashMap;
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
public class LoginControllerTest {

    @Autowired
    private LoginController controller;
    private RedirectAttributes redirect;

    public LoginControllerTest() {
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
     * Test of login method, of class LoginController.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String expResult = "login";
        String result = controller.login();
        assertEquals(expResult, result);
    }

    /**
     * Test of onSubmit method, of class LoginController.
     */
    @Test
    public void testOnSubmit_ValidAccount() throws Exception {
        System.out.println("onSubmit");
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        Object command = new Account(1);
        BindException errors = null;
        ModelAndView result = controller.onSubmit(request, response, command, errors);
        assertNotNull(result);
    }

    @Test
    public void testOnSubmit_NonAccount() throws Exception {
        System.out.println("onSubmit");
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        Object command = new String();
        BindException errors = null;
        try {
            ModelAndView result = controller.onSubmit(request, response, command, errors);
            assertNull(result);
        } catch (Exception e) {
            fail("Exception should have been thrown");
        }
    }

    @Test
    public void testOnSubmit_NullAccount() throws Exception {
        System.out.println("onSubmit");
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        Object command = null;
        BindException errors = null;
        ModelAndView result = controller.onSubmit(request, response, command, errors);
        assertNull(result);
    }

    /**
     * Test of handleLogin method, of class LoginController.
     */
    @Test
    public void testHandleLogin() throws Exception {
        System.out.println("handleLogin");
        String username = "Activated";
        String password = "1234";
        HttpSession session = new MockHttpSession();
        String expResult = "loginSuccess";
        try {
            String result = controller.handleLogin(username, password, redirect, session);
            assertEquals(expResult, result);
            assertTrue(session.getAttribute(LoginController.ACCOUNT_ATTRIBUTE) != null);
        } catch (Exception e) {
            fail("Should not throw exceptions");
        }
    }

    @Test
    public void testHandleLogin_InvalidName() throws Exception {
        System.out.println("handleLogin");
        String username = "Aed";
        String password = "1234";
        HttpSession session = new MockHttpSession();
        String expResult = "redirect:/login.htm";
        try {
            String result = controller.handleLogin(username, password, redirect, session);
            assertEquals(expResult, result);
            assertTrue(redirect.containsAttribute("exception"));
            assertTrue(redirect.getFlashAttributes().get("exception") instanceof AccountNotFoundException);
        } catch (Exception e) {
            fail("Should not throw exceptions");
        }
    }

    @Test
    public void testHandleLogin_InvalidPassword() throws Exception {
        System.out.println("handleLogin");
        String username = "Activated";
        String password = "4321";
        HttpSession session = new MockHttpSession();
        String expResult = "redirect:/login.htm";
        try {
            String result = controller.handleLogin(username, password, redirect, session);
            assertEquals(expResult, result);
            assertTrue(redirect.containsAttribute("exception"));
            assertTrue(redirect.getFlashAttributes().get("exception") instanceof AuthenticationException);
        } catch (Exception e) {
            fail("Should not throw exceptions");
        }
    }

    @Test
    public void testHandleLogin_NotActivated() throws Exception {
        System.out.println("handleLogin");
        String username = "NotActivated";
        String password = "1234";
        HttpSession session = new MockHttpSession();
        String expResult = "redirect:/login.htm";
        try {
            String result = controller.handleLogin(username, password, redirect, session);
            assertEquals(expResult, result);
            assertTrue(redirect.containsAttribute("exception"));
            assertTrue(redirect.getFlashAttributes().get("exception") instanceof AccountNotActivatedException);
        } catch (Exception e) {
            fail("Should not throw exceptions");
        }
    }

    @Test
    public void testHandleLogin_BlankInfo() throws Exception {
        System.out.println("handleLogin");
        String username = "";
        String password = "";
        HttpSession session = new MockHttpSession();
        String expResult = "redirect:/login.htm";
        try {
            String result = controller.handleLogin(username, password, redirect, session);
            assertEquals(expResult, result);
            assertTrue(redirect.containsAttribute("exception"));
            assertTrue(redirect.getFlashAttributes().get("exception") instanceof LoginException);
        } catch (Exception e) {
            fail("Should not throw exceptions");
        }
    }
}
