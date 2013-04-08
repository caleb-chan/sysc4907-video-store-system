/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.VideoInfo;
import com.team33.services.BrowseService;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/controller/controller-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true) 
@Transactional
public class BrowseControllerTest {
    
    @Autowired
    private BrowseController controller;
    
    public BrowseControllerTest() {
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
     * Test of displayVideoInformation method, of class BrowseController.
     */
    @Test
    public void testDisplayVideoInformation_ValidId() {
        System.out.println("displayVideoInformation");
        String videoId = "1";
        Map<String, Object> map = new HashMap<String, Object>();
        String expResult = "/browseView";
        String result = controller.displayVideoInformation(videoId, map);
        assertEquals(expResult, result);
        assertTrue(map.containsKey("videoInfo"));
        assertEquals(((VideoInfo)map.get("videoInfo")).getId().intValue(), 1);
        assertEquals(map.get("runningMin"), 40);
        assertEquals(map.get("runningHour"), 1);
    }
    
    @Test
    public void testDisplayVideoInformation_InvalidId() {
        System.out.println("displayVideoInformation");
        String videoId = "9999";
        Map<String, Object> map = new HashMap<String, Object>();
        String expResult = "/browseView";
        try{
            String result = controller.displayVideoInformation(videoId, map);
            assertEquals(expResult, result);
        }catch(Exception e){
            fail("Exception should not be thrown");
        }
    }
    
    @Test
    public void testDisplayVideoInformation_NonNumberId() {
        System.out.println("displayVideoInformation");
        String videoId = "A";
        Map<String, Object> map = new HashMap<String, Object>();
        String expResult = "/browseView";
        try{
            String result = controller.displayVideoInformation(videoId, map);
            assertEquals(expResult, result);
        }catch(Exception e){
            fail("Exception should not be thrown");
        }
    }
}
