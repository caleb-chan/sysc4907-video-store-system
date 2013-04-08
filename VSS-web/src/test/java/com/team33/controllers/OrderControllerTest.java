/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.form.OrderRequest;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.AuthenticationException;
import com.team33.services.exception.InsufficientFundsException;
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
    public void testCreateOrder_ValidOrderRequest() {
        System.out.println("createOrder");
        String referer = "";
        OrderRequest orderRequest = new OrderRequest();
        BindingResult result_2 = null;
        HttpSession session = new MockHttpSession();
        ShoppingCart cart = new ShoppingCart();
        session.setAttribute(LoginController.ACCOUNT_ATTRIBUTE, new Integer(1));
        session.setAttribute(ShoppingCartController.SHOPPING_CART_ATTRIBUTE_NAME, cart);
        String expResult = "redirect:/myAccountView.htm";
        String result = controller.createOrder(referer, orderRequest, result_2, session, redirect);
        assertEquals(expResult, result);
        assertNull(session.getAttribute(ShoppingCartController.SHOPPING_CART_ATTRIBUTE_NAME));
    }
    
    @Test
    @Rollback(true)
    public void testCreateOrder_ValidOrderRequest_NotActivated() {
        System.out.println("createOrder");
        String referer = "";
        OrderRequest orderRequest = new OrderRequest();
        BindingResult result_2 = null;
        HttpSession session = new MockHttpSession();
        ShoppingCart cart = new ShoppingCart();
        session.setAttribute(LoginController.ACCOUNT_ATTRIBUTE, new Integer(2));
        session.setAttribute(ShoppingCartController.SHOPPING_CART_ATTRIBUTE_NAME, cart);
        String expResult = "redirect:" + referer;
        String result = controller.createOrder(referer, orderRequest, result_2, session, redirect);
        assertEquals(expResult, result);
        assertTrue(redirect.containsAttribute("exception"));
        assertTrue(redirect.getFlashAttributes().get("exception") instanceof AccountNotActivatedException);
    }
    
    @Test
    @Rollback(true)
    public void testCreateOrder_ValidOrderRequest_NoMoney() {
        System.out.println("createOrder");
        String referer = "";
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setTotalPrice(9999);
        BindingResult result_2 = null;
        HttpSession session = new MockHttpSession();
        ShoppingCart cart = new ShoppingCart();
        session.setAttribute(LoginController.ACCOUNT_ATTRIBUTE, new Integer(2));
        session.setAttribute(ShoppingCartController.SHOPPING_CART_ATTRIBUTE_NAME, cart);
        String expResult = "redirect:" + referer;
        String result = controller.createOrder(referer, orderRequest, result_2, session, redirect);
        assertEquals(expResult, result);
        assertTrue(redirect.containsAttribute("exception"));
        assertTrue(redirect.getFlashAttributes().get("exception") instanceof InsufficientFundsException);
    }
    
    @Test
    @Rollback(true)
    public void testCreateOrder_ValidOrderRequest_AddPurchase() {
        System.out.println("createOrder");
        String referer = "";
        OrderRequest orderRequest = new OrderRequest();
        BindingResult result_2 = null;
        HttpSession session = new MockHttpSession();
        ShoppingCart cart = new ShoppingCart();
        cart.addToCart(1, false);
        session.setAttribute(LoginController.ACCOUNT_ATTRIBUTE, new Integer(1));
        session.setAttribute(ShoppingCartController.SHOPPING_CART_ATTRIBUTE_NAME, cart);
        String expResult = "redirect:/myAccountView.htm";
        String result = controller.createOrder(referer, orderRequest, result_2, session, redirect);
        assertEquals(expResult, result);
        assertNull(session.getAttribute(ShoppingCartController.SHOPPING_CART_ATTRIBUTE_NAME));
    }
    
    @Test
    @Rollback(true)
    public void testCreateOrder_ValidOrderRequest_AddRental() {
        System.out.println("createOrder");
        String referer = "";
        OrderRequest orderRequest = new OrderRequest();
        BindingResult result_2 = null;
        HttpSession session = new MockHttpSession();
        ShoppingCart cart = new ShoppingCart();
        cart.addToCart(1, true);
        session.setAttribute(LoginController.ACCOUNT_ATTRIBUTE, new Integer(1));
        session.setAttribute(ShoppingCartController.SHOPPING_CART_ATTRIBUTE_NAME, cart);
        String expResult = "redirect:/myAccountView.htm";
        try{
            String result = controller.createOrder(referer, orderRequest, result_2, session, redirect);
            assertEquals(expResult, result);
        }catch(Exception e){
            fail("Exception should not be thrown");
        }
        assertNull(session.getAttribute(ShoppingCartController.SHOPPING_CART_ATTRIBUTE_NAME));
    }

    /**
     * Test of newOrder method, of class OrderController.
     */
    @Test
    @Rollback(true)
    public void testNewOrder_NullCart() {
        System.out.println("newOrder");
        String referer = "";
        Map<String, Object> model = new HashMap<String, Object>();
        HttpSession session = new MockHttpSession();
        String expResult = "redirect:" + referer;
        String result = controller.newOrder(referer, model, session);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    @Test
    @Rollback(true)
    public void testNewOrder_ValidCart() {
        System.out.println("newOrder");
        String referer = "";
        Map<String, Object> model = new HashMap<String, Object>();
        HttpSession session = new MockHttpSession();
        String expResult = "newOrder";
        ShoppingCart cart = new ShoppingCart();
        session.setAttribute(LoginController.ACCOUNT_ATTRIBUTE, new Integer(1));
        session.setAttribute(ShoppingCartController.SHOPPING_CART_ATTRIBUTE_NAME, cart);
        try{
            String result = controller.newOrder(referer, model, session);
            assertEquals(expResult, result);
        }catch(Exception e){
            fail("Exception should not be thrown");
        }
        assertTrue(model.containsKey("account"));
        assertEquals(model.get("totalPrice"), 0);
        assertEquals(model.get("uuid"), 1);
    }
    
    @Test
    @Rollback(true)
    public void testNewOrder_ValidCart_NotActivatedId() {
        System.out.println("newOrder");
        String referer = "";
        Map<String, Object> model = new HashMap<String, Object>();
        HttpSession session = new MockHttpSession();
        String expResult = "newOrder";
        ShoppingCart cart = new ShoppingCart();
        session.setAttribute(LoginController.ACCOUNT_ATTRIBUTE, new Integer(2));
        session.setAttribute(ShoppingCartController.SHOPPING_CART_ATTRIBUTE_NAME, cart);
        try{
            String result = controller.newOrder(referer, model, session);
            assertEquals(expResult, result);
        }catch(Exception e){
            fail("Exception should not be thrown");
        }
        assertTrue(model.containsKey("account"));
        assertEquals(model.get("totalPrice"), 0);
        assertEquals(model.get("uuid"), 2);
    }
    
    @Test
    @Rollback(true)
    public void testNewOrder_ValidCart_InvalidId() {
        System.out.println("newOrder");
        String referer = "";
        Map<String, Object> model = new HashMap<String, Object>();
        HttpSession session = new MockHttpSession();
        String expResult = "newOrder";
        ShoppingCart cart = new ShoppingCart();
        session.setAttribute(LoginController.ACCOUNT_ATTRIBUTE, new Integer(9999));
        session.setAttribute(ShoppingCartController.SHOPPING_CART_ATTRIBUTE_NAME, cart);
        try{
            String result = controller.newOrder(referer, model, session);
            assertEquals(expResult, result);
        }catch(Exception e){
            fail("Exception should not be thrown");
        }
        assertTrue(model.containsKey("account"));
        assertEquals(model.get("totalPrice"), 0);
        assertEquals(model.get("uuid"), 9999);
    }
}
