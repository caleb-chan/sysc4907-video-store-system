/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao.playback;

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
public class VideoPlaybackDaoImplTest {
    
    @Autowired
    private VideoPlaybackDaoImpl videoPlaybackDao;
    
    public VideoPlaybackDaoImplTest() {
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
     * Test of getFileInformation method, of class VideoPlaybackDaoImpl.
     */
    @Test
    public void testGetFileInformation_ValidVideoId() {
        System.out.println("getFileInformation");
        String file = null;
        try{
            file = videoPlaybackDao.getFileInformation(1);
        }catch(DataAccessException e){
            fail("DataAccessException should not be thrown");
        }
        assertNotNull(file);
        assertEquals(file, "Test1");
    }
    
    @Test
    public void testGetFileInformation_NedVideoId() {
        System.out.println("getFileInformation");
        String file = null;
        try{
            file = videoPlaybackDao.getFileInformation(-1);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
            
        }
    }
    
    @Test
    public void testGetFileInformation_InvideoId() {
        System.out.println("getFileInformation");
        String file = null;
        try{
            file = videoPlaybackDao.getFileInformation(9999);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
            
        }
    }
    
    

    /**
     * Test of getVideoPlaybackInformation method, of class VideoPlaybackDaoImpl.
     */
    
    @Test
    public void testGetVideoPlaybackInformation_ValidOrderId() {
        System.out.println("getVideoPlaybackInformation");
        int info = -1;
        try{
            info = videoPlaybackDao.getVideoPlaybackInformation(1, 1, 1);
        }catch(DataAccessException e){
            fail("DataAccessException should not be thrown");
        }
        assertTrue(info != -1);
    }
    
    
    @Test
    public void testGetVideoPlaybackInformation_InvalidOrderId() {
        System.out.println("getVideoPlaybackInformation");
        int info = -1;
        try{
            info = videoPlaybackDao.getVideoPlaybackInformation(9999, 1, 1);
            fail("DataAccessException should not be thrown");
        }catch(DataAccessException e){
        }
    }
    
    @Test
    public void testGetVideoPlaybackInformation_NegOrderId() {
        System.out.println("getVideoPlaybackInformation");
        int info = -1;
        try{
            info = videoPlaybackDao.getVideoPlaybackInformation(-1, 1, 1);
            fail("DataAccessException should not be thrown");
        }catch(DataAccessException e){
            
        }
    }
    
    @Test
    public void testGetVideoPlaybackInformation_NegVideoId() {
        System.out.println("getVideoPlaybackInformation");
        int info = -1;
        try{
            info = videoPlaybackDao.getVideoPlaybackInformation(1, -1, 1);
            fail("DataAccessException should not be thrown");
        }catch(DataAccessException e){
        }
    }
    
    @Test
    public void testGetVideoPlaybackInformation_InvalidVideoId() {
        System.out.println("getVideoPlaybackInformation");
        int info = -1;
        try{
            info = videoPlaybackDao.getVideoPlaybackInformation(1, 9999, 1);
            fail("DataAccessException should not be thrown");
        }catch(DataAccessException e){
        }
    }
    
    @Test
    public void testGetVideoPlaybackInformation_NegAccountId() {
        System.out.println("getVideoPlaybackInformation");
        int info = -1;
        try{
            info = videoPlaybackDao.getVideoPlaybackInformation(1, 1, -1);
            fail("DataAccessException should not be thrown");
        }catch(DataAccessException e){
        }
    }
    
    @Test
    public void testGetVideoPlaybackInformation_InvalidAccountId() {
        System.out.println("getVideoPlaybackInformation");
        int info = -1;
        try{
            info = videoPlaybackDao.getVideoPlaybackInformation(1, 1, 9999);
            fail("DataAccessException should not be thrown");
        }catch(DataAccessException e){
        }
    }
    
    
    

    /**
     * Test of saveVideoPlaybackInformation method, of class VideoPlaybackDaoImpl.
     */
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_ValidOrderId() {
        System.out.println("saveVideoPlaybackInformation");
        try{
            videoPlaybackDao.saveVideoPlaybackInformation(1, 1, 1, 100);
        }catch(DataAccessException e){
            fail("DataAccessException should not be thrown");
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_NegOrderId() {
        System.out.println("saveVideoPlaybackInformation");
        try{
            videoPlaybackDao.saveVideoPlaybackInformation(-1, 1, 1, 100);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
            
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_InvalidOrderId() {
        System.out.println("saveVideoPlaybackInformation");
        try{
            videoPlaybackDao.saveVideoPlaybackInformation(9999, 1, 1, 100);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_NegVideoId() {
        System.out.println("saveVideoPlaybackInformation");
        try{
            videoPlaybackDao.saveVideoPlaybackInformation(1, -1, 1, 100);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
            
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_InvalidVideoId() {
        System.out.println("saveVideoPlaybackInformation");
        try{
            videoPlaybackDao.saveVideoPlaybackInformation(1, 9999, 1, 100);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_NegAccountId() {
        System.out.println("saveVideoPlaybackInformation");
        try{
            videoPlaybackDao.saveVideoPlaybackInformation(1, 1, -1, 100);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
            
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_InvalidAccountId() {
        System.out.println("saveVideoPlaybackInformation");
        try{
            videoPlaybackDao.saveVideoPlaybackInformation(1, 1, 9999, 100);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_NegSavedTime() {
        System.out.println("saveVideoPlaybackInformation");
        try{
            videoPlaybackDao.saveVideoPlaybackInformation(1, 1, 1, -1);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoPlaybackInformation_InvalidTime() {
        System.out.println("saveVideoPlaybackInformation");
        try{
            videoPlaybackDao.saveVideoPlaybackInformation(1, 1, 1, 9999);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }
    }

}
