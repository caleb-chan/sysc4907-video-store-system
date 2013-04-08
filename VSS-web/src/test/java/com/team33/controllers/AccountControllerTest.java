/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.Account;
import com.team33.services.AccountService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/controller/controller-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    public AccountControllerTest() {
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
     * Test of getAccounts method, of class AccountController.
     */
    @Test
    public void testGetAccounts() {
        System.out.println("getAccounts");
        Map<String, Object> map = new HashMap<String, Object>();
        String expResult = "/registerAccountView";
        String result = accountController.getAccounts(map);
        assertEquals(expResult, result);
        assertEquals(map.size(), 2);
        assertEquals(map.get("account"), new Account());
        assertTrue(map.containsKey("accountList"));
        assertTrue(map.get("accountList") instanceof List);
    }

    /**
     * Test of saveAccount method, of class AccountController.
     */
    @Test
    public void testSaveAccount() {
        System.out.println("saveAccount");
        Account account = null;
        BindingResult result_2 = null;
        AccountController instance = new AccountController();
        String expResult = "/registerAccountView";
        String result = instance.saveAccount(account, result_2);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeAccount method, of class AccountController.
     */
    @Test
    public void testRemoveAccount_ValidAccountId() {
        System.out.println("removeAccount");
        String expResult = "redirect:/registerAccountView";
        String result = accountController.removeAccount(1);
        assertEquals(expResult, result);
    }

    @Test
    public void testRemoveAccount_InvalidAccountId() {
        System.out.println("removeAccount");
        String expResult = "redirect:/registerAccountView";
        try {
            String result = accountController.removeAccount(9999);
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("No Exception should be thrown");
        }
    }

    @Test
    public void testRemoveAccount_NegAccountId() {
        System.out.println("removeAccount");
        String expResult = "redirect:/registerAccountView";
        try {
            String result = accountController.removeAccount(-1);
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("No Exception should be thrown");
        }
    }
}
