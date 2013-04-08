/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.controllers.ShoppingCart.ShoppingCartKey;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daywalker
 */
public class ShoppingCartTest {
    
    public ShoppingCartTest() {
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
    
    @Test
    public void testShoppingCartClass(){
        System.out.println("Shopping Cart()");
        ShoppingCart instance = new ShoppingCart();
        assertNotNull(instance);
        assertNotNull(instance.getShoppingCartList());
    }

    /**
     * Test of addToCart method, of class ShoppingCart.
     */
    @Test
    public void testAddToCart() {
        System.out.println("testAddToCart");
        int videoId = 0;
        boolean isRented = true;
        ShoppingCart instance = new ShoppingCart();
        assertEquals(0, instance.getShoppingCartList().size());
        instance.addToCart(videoId, isRented);
        assertEquals(1, instance.getShoppingCartList().size());
        ShoppingCart.ShoppingCartKey key = instance.new ShoppingCartKey(videoId, isRented);
        assertTrue(instance.getShoppingCartList().containsKey(key));
        assertEquals(1, instance.getShoppingCartList().get(key).intValue());
    }
    
    @Test
    public void testAddToCart_NegId(){
        System.out.println("testAddToCart_NegId");
        int videoId = -1;
        boolean isRented = true;
        ShoppingCart instance = new ShoppingCart();
        assertEquals(0, instance.getShoppingCartList().size());
        instance.addToCart(videoId, isRented);
        assertEquals(1, instance.getShoppingCartList().size());
        ShoppingCart.ShoppingCartKey key = instance.new ShoppingCartKey(videoId, isRented);
        assertTrue(instance.getShoppingCartList().containsKey(key));
        assertEquals(1, instance.getShoppingCartList().get(key).intValue());
    }
    
    @Test
    public void testAddToCart_NotRented(){
        System.out.println("testAddToCart_NotRented");
        int videoId = -1;
        boolean isRented = false;
        ShoppingCart instance = new ShoppingCart();
        assertEquals(0, instance.getShoppingCartList().size());
        instance.addToCart(videoId, isRented);
        assertEquals(1, instance.getShoppingCartList().size());
        ShoppingCart.ShoppingCartKey key = instance.new ShoppingCartKey(videoId, isRented);
        assertTrue(instance.getShoppingCartList().containsKey(key));
        assertEquals(1, instance.getShoppingCartList().get(key).intValue());
    }
    
    @Test
    public void testDuplicateToCart(){
        System.out.println("testAddAdditionalToCart");
        int videoId = 0;
        boolean isRented = true;
        ShoppingCart instance = new ShoppingCart();
        assertEquals(0, instance.getShoppingCartList().size());
        instance.addToCart(videoId, isRented);
        assertEquals(1, instance.getShoppingCartList().size());
        ShoppingCart.ShoppingCartKey key = instance.new ShoppingCartKey(videoId, isRented);
        assertEquals(1, instance.getShoppingCartList().get(key).intValue());
    }
    
    @Test
    public void testAddAdditionalToCart(){
        System.out.println("testAddAdditionalToCart");
        int videoId1 = 0;
        boolean isRented1 = true;
        int videoId2 = 0;
        boolean isRented2 = true;
        ShoppingCart instance = new ShoppingCart();
        assertEquals(0, instance.getShoppingCartList().size());
        instance.addToCart(videoId1, isRented1);
        instance.addToCart(videoId2, isRented2);
        assertEquals(2, instance.getShoppingCartList().size());
    }

    /**
     * Test of removeFromCart method, of class ShoppingCart.
     */
    @Test
    public void testRemoveFromCart() {
        System.out.println("removeFromCart");
        int videoId = 0;
        boolean isRented = false;
        ShoppingCart instance = new ShoppingCart();
        instance.addToCart(videoId, isRented);
        assertEquals(1, instance.getShoppingCartList().size());
        instance.removeFromCart(videoId, isRented);
        assertEquals(0, instance.getShoppingCartList().size());
    }
    
    @Test
    public void testRemoveFromCart_NegId() {
        System.out.println("removeFromCart");
        int videoId = -1;
        boolean isRented = false;
        ShoppingCart instance = new ShoppingCart();
        instance.addToCart(videoId, isRented);
        assertEquals(1, instance.getShoppingCartList().size());
        instance.removeFromCart(videoId, isRented);
        assertEquals(0, instance.getShoppingCartList().size());
    }
    
    @Test
    public void testRemoveFromCart_IsRented() {
        System.out.println("removeFromCart");
        int videoId = -1;
        boolean isRented = true;
        ShoppingCart instance = new ShoppingCart();
        instance.addToCart(videoId, isRented);
        assertEquals(1, instance.getShoppingCartList().size());
        instance.removeFromCart(videoId, isRented);
        assertEquals(0, instance.getShoppingCartList().size());
    }
    
    @Test
    public void testRemoveFromCart_RemoveEmptyList() {
        System.out.println("removeFromCart");
        int videoId = -1;
        boolean isRented = false;
        ShoppingCart instance = new ShoppingCart();
        assertEquals(0, instance.getShoppingCartList().size());
        try{
            instance.removeFromCart(videoId, isRented);
        }catch(Exception e){
            fail("Should not throw error");
        }
        assertEquals(0, instance.getShoppingCartList().size());
    }

    /**
     * Test of getVideoList method, of class ShoppingCart.
     */
    @Test
    public void testGetVideoList() {
        System.out.println("getVideoList");
        ShoppingCart instance = new ShoppingCart();
        List expResult = null;
        List result = instance.getVideoList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShoppingCartInfo method, of class ShoppingCart.
     */
    @Test
    public void testGetShoppingCartInfo() {
        System.out.println("getShoppingCartInfo");
        ShoppingCart instance = new ShoppingCart();
        List expResult = null;
        List result = instance.getShoppingCartInfo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPurchaseList method, of class ShoppingCart.
     */
    @Test
    public void testGetPurchaseList() {
        System.out.println("getPurchaseList");
        ShoppingCart instance = new ShoppingCart();
        Set expResult = null;
        Set result = instance.getPurchaseList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRentedList method, of class ShoppingCart.
     */
    @Test
    public void testGetRentedList() {
        System.out.println("getRentedList");
        ShoppingCart instance = new ShoppingCart();
        Set expResult = null;
        Set result = instance.getRentedList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ShoppingCart.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ShoppingCart instance = new ShoppingCart();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
