/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services.playback;

import com.team33.entities.dao.VideoAccessDao;
import com.team33.entities.dao.playback.VideoPlaybackDao;
import com.team33.services.exception.DataAccessException;
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

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/service/service-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true) 
@Transactional
public class VideoPlaybackServiceImplTest {
    
    @Autowired
    private VideoPlaybackServiceImpl videoServiceImpl;
    
    public VideoPlaybackServiceImplTest() {
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
     * Test of saveInformation method, of class VideoPlaybackServiceImpl.
     */    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_ValidLoginId() {
        System.out.println("testSaveVideoPlaybackInformation_ValidLoginId");
        try{
            videoServiceImpl.saveInformation(1, 1, 1, 100);
        }catch(DataAccessException e){
            fail("DataAccessException should not be thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_NegLoginId() {
        System.out.println("testSaveVideoPlaybackInformation_NegLoginId");
        try{
            videoServiceImpl.saveInformation(-1, 1, 1, 100);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
            
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_InvalidLoginId() {
        System.out.println("testSaveVideoPlaybackInformation_InvalidLoginId");
        try{
            videoServiceImpl.saveInformation(9999, 1, 1, 100);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_NegOrderId() {
        System.out.println("testSaveVideoPlaybackInformation_NegOrderId");
        try{
            videoServiceImpl.saveInformation(1, -1, 1, 100);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
            
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_InvalidOrderId() {
        System.out.println("testSaveVideoPlaybackInformation_InvalidOrderId");
        try{
            videoServiceImpl.saveInformation(1, 9999, 1, 100);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_NegVideoId() {
        System.out.println("testSaveVideoPlaybackInformation_NegVideoId");
        try{
            videoServiceImpl.saveInformation(1, 1, -1, 100);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
            
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_InvalidVideoId() {
        System.out.println("testSaveVideoPlaybackInformation_InvalidVideoId");
        try{
            videoServiceImpl.saveInformation(1, 1, 9999, 100);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_NegSavedTime() {
        System.out.println("testSaveVideoPlaybackInformation_NegSavedTime");
        try{
            videoServiceImpl.saveInformation(1, 1, 1, -1);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_InvalidTime() {
        System.out.println("testSaveVideoPlaybackInformation_InvalidTime");
        try{
            videoServiceImpl.saveInformation(1, 1, 1, 9999);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }
    }
    /**
     * Test of getInformation method, of class VideoPlaybackServiceImpl.
     */
    @Test
    public void testGetInformation() {
        System.out.println("getInformation");
        int loginToken = 0;
        int orderId = 0;
        int videoId = 0;
        VideoPlaybackServiceImpl instance = new VideoPlaybackServiceImpl();
        int expResult = 0;
        int result = instance.getInformation(loginToken, orderId, videoId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVideoFileInformation method, of class VideoPlaybackServiceImpl.
     */
    @Test
    public void testGetVideoFileInformation() {
        System.out.println("getVideoFileInformation");
        int loginToken = 0;
        int videoId = 0;
        VideoPlaybackServiceImpl instance = new VideoPlaybackServiceImpl();
        String expResult = "";
        String result = instance.getVideoFileInformation(loginToken, videoId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testGetFileInformation_ValidLoginId() {
        System.out.println("getFileInformation");
        String file = null;
        try{
            file = videoServiceImpl.getVideoFileInformation(1, 1);
        }catch(DataAccessException e){
            fail("DataAccessException should not be thrown");
        }
        assertNotNull(file);
        assertEquals(file, "Test1");
    }
    
    @Test
    public void testGetFileInformation_NedLoginId() {
        System.out.println("getFileInformation");
        String file = null;
        try{
            file = videoServiceImpl.getVideoFileInformation(-1, 1);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }
    }
    @Test
    public void testGetFileInformation_InvalidId() {
        System.out.println("getFileInformation");
        String file = null;
        try{
            file = videoServiceImpl.getVideoFileInformation(9999, 1);
        }catch(DataAccessException e){
            
        }
        assertEquals(file, "");
    }
    
    @Test
    public void testGetFileInformation_NedVideoId() {
        System.out.println("getFileInformation");
        String file = null;
        try{
            file = videoServiceImpl.getVideoFileInformation(1, -1);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
            
        }
    }
    
    @Test
    public void testGetFileInformation_InvideoId() {
        System.out.println("getFileInformation");
        String file = null;
        try{
            file = videoServiceImpl.getVideoFileInformation(1, 9999);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
            
        }
    }
}
