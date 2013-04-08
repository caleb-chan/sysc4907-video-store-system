/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.services.exception.RegistrationException;
import com.team33.services.playback.VideoPlaybackService;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/controller/controller-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true) 
@Transactional
public class VideoViewControllerTest {
    
    @Autowired
    private VideoViewController controller;
    private RedirectAttributes redirect;
    
    public VideoViewControllerTest() {
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
     * Test of createDefaultLoginToken method, of class VideoViewController.
     */
    @Test
    public void testCreateDefaultLoginToken() {
        System.out.println("createDefaultLoginToken");
        Integer expResult = null;
        Integer result = controller.createDefaultLoginToken();
        assertEquals(expResult, result);
    }
    /**
     * Test of loadVideoOnStartup method, of class VideoViewController.
     */
    @Test
    @Rollback(true)
    public void testLoadVideoOnStartup() {
        System.out.println("loadVideoOnStartup");
        String referer = "";
        Map<String, Object> map = new HashMap<String, Object>();
        String orderId = "1";
        String videoId = "1";
        Integer tokenId = new Integer(1);
        String expResult = "/viewVideo";
        String result = controller.loadVideoOnStartup(referer, redirect, map, orderId, videoId, tokenId);
        assertEquals(expResult, result);
        assertEquals(map.get("currentTime"), 100);
        assertEquals(map.get("vidLocation_ogg"), "big_buck_bunny_480p_stereo.ogg");
        assertEquals(map.get("videoId"), 1);
        assertEquals(map.get("orderId"), 1);
    }
    
    @Test
    @Rollback(true)
    public void testLoadVideoOnStartup_InvalidTokenId() {
        System.out.println("loadVideoOnStartup");
        String referer = "";
        Map<String, Object> map = new HashMap<String, Object>();
        String orderId = "1";
        String videoId = "1";
        Integer tokenId = new Integer(9999);
        String expResult = "redirect:";
        try{
            String result = controller.loadVideoOnStartup(referer, redirect, map, orderId, videoId, tokenId);
            assertEquals(expResult, result);
            assertTrue(redirect.containsAttribute("exception"));
            assertTrue(redirect.getFlashAttributes().get("exception") instanceof String);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testLoadVideoOnStartup_NegTokenId() {
        System.out.println("loadVideoOnStartup");
        String referer = "";
        Map<String, Object> map = new HashMap<String, Object>();
        String orderId = "1";
        String videoId = "1";
        Integer tokenId = new Integer(-1);
        String expResult = "redirect:";
        try{
            String result = controller.loadVideoOnStartup(referer, redirect, map, orderId, videoId, tokenId);
            assertEquals(expResult, result);
            assertTrue(redirect.containsAttribute("exception"));
            assertTrue(redirect.getFlashAttributes().get("exception") instanceof String);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testLoadVideoOnStartup_NullToken() {
        System.out.println("loadVideoOnStartup");
        String referer = "";
        Map<String, Object> map = new HashMap<String, Object>();
        String orderId = "1";
        String videoId = "1";
        Integer tokenId = null;
        String expResult = "redirect:/login.htm";
        try{
            String result = controller.loadVideoOnStartup(referer, redirect, map, orderId, videoId, tokenId);
            assertEquals(expResult, result);
            assertTrue(redirect.containsAttribute("exception"));
            assertTrue(redirect.getFlashAttributes().get("exception") instanceof String);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testLoadVideoOnStartup_InvalidVideoId() {
        System.out.println("loadVideoOnStartup");
        String referer = "";
        Map<String, Object> map = new HashMap<String, Object>();
        String orderId = "1";
        String videoId = "9999";
        Integer tokenId = null;
        String expResult = "redirect:";
        try{
            String result = controller.loadVideoOnStartup(referer, redirect, map, orderId, videoId, tokenId);
            assertEquals(expResult, result);
            assertTrue(redirect.containsAttribute("exception"));
            assertTrue(redirect.getFlashAttributes().get("exception") instanceof String);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testLoadVideoOnStartup_NegVideoId() {
        System.out.println("loadVideoOnStartup");
        String referer = "";
        Map<String, Object> map = new HashMap<String, Object>();
        String orderId = "1";
        String videoId = "-1";
        Integer tokenId = null;
        String expResult = "redirect:";
        try{
            String result = controller.loadVideoOnStartup(referer, redirect, map, orderId, videoId, tokenId);
            assertEquals(expResult, result);
            assertTrue(redirect.containsAttribute("exception"));
            assertTrue(redirect.getFlashAttributes().get("exception") instanceof String);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testLoadVideoOnStartup_InvalidOrderId() {
        System.out.println("loadVideoOnStartup");
        String referer = "";
        Map<String, Object> map = new HashMap<String, Object>();
        String orderId = "9999";
        String videoId = "1";
        Integer tokenId = null;
        String expResult = "redirect:";
        try{
            String result = controller.loadVideoOnStartup(referer, redirect, map, orderId, videoId, tokenId);
            assertEquals(expResult, result);
            assertTrue(redirect.containsAttribute("exception"));
            assertTrue(redirect.getFlashAttributes().get("exception") instanceof String);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testLoadVideoOnStartup_NegOrderId() {
        System.out.println("loadVideoOnStartup");
        String referer = "";
        Map<String, Object> map = new HashMap<String, Object>();
        String orderId = "-1";
        String videoId = "1";
        Integer tokenId = null;
        String expResult = "redirect:";
        try{
            String result = controller.loadVideoOnStartup(referer, redirect, map, orderId, videoId, tokenId);
            assertEquals(expResult, result);
            assertTrue(redirect.containsAttribute("exception"));
            assertTrue(redirect.getFlashAttributes().get("exception") instanceof String);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }

    /**
     * Test of saveVideoTimeLocation method, of class VideoViewController.
     */
    @Test
    @Rollback(true)
    public void testSaveVideoTimeLocation() {
        System.out.println("saveVideoTimeLocation");
        String currentTime = "100";
        String orderId = "1";
        String videoId = "1";
        Integer tokenId = new Integer(1);
        try{
            controller.saveVideoTimeLocation(currentTime, orderId, videoId, tokenId);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoTimeLocation_InvalidTokenId() {
        System.out.println("saveVideoTimeLocation");
        String currentTime = "100";
        String orderId = "1";
        String videoId = "1";
        Integer tokenId = new Integer(9999);
        try{
            controller.saveVideoTimeLocation(currentTime, orderId, videoId, tokenId);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoTimeLocation_NotActivated() {
        System.out.println("saveVideoTimeLocation");
        String currentTime = "100";
        String orderId = "1";
        String videoId = "1";
        Integer tokenId = new Integer(2);
        try{
            controller.saveVideoTimeLocation(currentTime, orderId, videoId, tokenId);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoTimeLocation_NegTokenId() {
        System.out.println("saveVideoTimeLocation");
        String currentTime = "100";
        String orderId = "1";
        String videoId = "1";
        Integer tokenId = new Integer(-1);
        try{
            controller.saveVideoTimeLocation(currentTime, orderId, videoId, tokenId);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoTimeLocation_InvalidVideoId() {
        System.out.println("saveVideoTimeLocation");
        String currentTime = "100";
        String orderId = "1";
        String videoId = "9999";
        Integer tokenId = new Integer(1);
        try{
            controller.saveVideoTimeLocation(currentTime, orderId, videoId, tokenId);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoTimeLocation_NegVideoId() {
        System.out.println("saveVideoTimeLocation");
        String currentTime = "100";
        String orderId = "1";
        String videoId = "-1";
        Integer tokenId = new Integer(1);
        try{
            controller.saveVideoTimeLocation(currentTime, orderId, videoId, tokenId);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoTimeLocation_InvalidOrderId() {
        System.out.println("saveVideoTimeLocation");
        String currentTime = "100";
        String orderId = "9999";
        String videoId = "1";
        Integer tokenId = new Integer(1);
        try{
            controller.saveVideoTimeLocation(currentTime, orderId, videoId, tokenId);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    @Test
    @Rollback(true)
    public void testSaveVideoTimeLocation_NegOrderId() {
        System.out.println("saveVideoTimeLocation");
        String currentTime = "100";
        String orderId = "-1";
        String videoId = "1";
        Integer tokenId = new Integer(1);
        try{
            controller.saveVideoTimeLocation(currentTime, orderId, videoId, tokenId);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    @Test
    @Rollback(true)
    public void testSaveVideoTimeLocation_NegCurrentTime() {
        System.out.println("saveVideoTimeLocation");
        String currentTime = "-1";
        String orderId = "1";
        String videoId = "1";
        Integer tokenId = new Integer(1);
        try{
            controller.saveVideoTimeLocation(currentTime, orderId, videoId, tokenId);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
    @Test
    @Rollback(true)
    public void testSaveVideoTimeLocation_InvalidCurrentTime() {
        System.out.println("saveVideoTimeLocation");
        String currentTime = "9999";
        String orderId = "1";
        String videoId = "1";
        Integer tokenId = new Integer(1);
        try{
            controller.saveVideoTimeLocation(currentTime, orderId, videoId, tokenId);
        }catch(Exception e){
            fail("Exception should not have been thrown");
        }
    }
}
