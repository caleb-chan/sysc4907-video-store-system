/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.VideoInfo;
import com.team33.services.BrowseService;
import com.team33.services.VideoAccessService;
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

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/controller/controller-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true) 
@Transactional
public class BrowseVideosControllerTest {
    
    @Autowired
    private BrowseVideosController controller;
    
    public BrowseVideosControllerTest() {
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
     * Test of getVideos method, of class BrowseVideosController.
     */
    @Test
    public void testGetVideos_NoSpecificSearch() {
        System.out.println("getVideos");
        Map<String, Object> map = null;
        String search = null;
        String expResult = "/browseVideos";
        String result = controller.getVideos(map, search);
        assertEquals(expResult, result);
        List<VideoInfo> resultList = (List<VideoInfo>)map.get("videoInfoList");
        assertTrue(!resultList.isEmpty());
        assertEquals(resultList.get(0).getTitle(), "Test1");
    }
    @Test
    public void testGetVideos_SpecificSearch() {
        System.out.println("getVideos");
        Map<String, Object> map = null;
        String search = "Te";
        String expResult = "/browseVideos";
        String result = controller.getVideos(map, search);
        assertEquals(expResult, result);
        List<VideoInfo> resultList = (List<VideoInfo>)map.get("videoInfoList");
        assertTrue(!resultList.isEmpty());
        assertEquals(resultList.get(0).getTitle(), "Test1");
    }
    @Test
    public void testGetVideos_SpecificSearch_NoResult() {
        System.out.println("getVideos");
        Map<String, Object> map = null;
        String search = "MM";
        String expResult = "/browseVideos";
        String result = controller.getVideos(map, search);
        assertEquals(expResult, result);
        List<VideoInfo> resultList = (List<VideoInfo>)map.get("videoInfoList");
        assertTrue(resultList.isEmpty());
    }
}
