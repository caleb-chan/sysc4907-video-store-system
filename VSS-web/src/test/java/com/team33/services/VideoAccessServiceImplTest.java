/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.VideoInfo;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/service/service-test.xml"})
@Transactional
public class VideoAccessServiceImplTest {
    
    @Autowired
    private VideoAccessServiceImpl videoAccessServiceImpl;
    
    public VideoAccessServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //this.videoAccessServiceImpl = new VideoAccessServiceImpl();
        //this.videoAccessServiceImpl.setVideoAccessDao(new VideoAccessDaoImplTestStub());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isActivated method, of class VideoAccessServiceImpl.
     */
    @Test
    public void testIsActivated_ValidId() {
        try{
            assertTrue(this.videoAccessServiceImpl.isActivated(1));
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testIsActivated_ValidId_NotActivated() {
        try{
            assertFalse(this.videoAccessServiceImpl.isActivated(2));
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testIsActivated_NegId(){
        try{
            assertTrue(this.videoAccessServiceImpl.isActivated(-1));
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testIsActivated_InvalidId(){
        try{
            assertTrue(this.videoAccessServiceImpl.isActivated(9999));
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    /**
     * Test of getVideoInfo method, of class VideoAccessServiceImpl.
     */
    @Test
    public void testGetVideoInfo_ValidVideoId() {
        VideoInfo info = null;
        try{
            info = this.videoAccessServiceImpl.getVideoInfo(1, 1);
        }catch(DataAccessException e){
            fail("DataAccessException should not be thrown");
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
        assertNotNull(info);
        assertEquals(info.getId().intValue(), 1);
    }
    
    @Test
    public void testGetVideoInfo_ValidVideoId_NotActivated() {
        VideoInfo info = null;
        try{
            info = this.videoAccessServiceImpl.getVideoInfo(1, 2);
        }catch(DataAccessException e){
            fail("DataAccessException should not be thrown");
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
        assertNull(info);
    }
    
    @Test
    public void testGetVideoInfo_NegVideoId(){
        VideoInfo info = null;
        try{
            info = this.videoAccessServiceImpl.getVideoInfo(-1, 1);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testGetVideoInfo_InvalidVideoId(){
        VideoInfo info = null;
        try{
            info = this.videoAccessServiceImpl.getVideoInfo(9999, 1);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testGetVideoInfo_NegAccountId(){
        VideoInfo info = null;
        try{
            info = this.videoAccessServiceImpl.getVideoInfo(1, -1);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testGetVideoInfo_InvalidAccountId(){
        VideoInfo info = null;
        try{
            info = this.videoAccessServiceImpl.getVideoInfo(1, 9999);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }

    /**
     * Test of getVideoInfoList method, of class VideoAccessServiceImpl.
     */
    @Test
    public void testGetVideoInfoList_NegId() {
        List<VideoInfo> info = null;
        try{
            info = this.videoAccessServiceImpl.getVideoInfoList(-1);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
        }
    }
    
    @Test
    public void testGetVideoInfoList_InvaidId() {
        List<VideoInfo> info = null;
        try{
            info = this.videoAccessServiceImpl.getVideoInfoList(9999);
            fail("DataAccessException should be thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
    }
    
    @Test
    public void testGetVideoInfoList_ValidId(){
        List<VideoInfo> info = null;
        try{
            info = this.videoAccessServiceImpl.getVideoInfoList(1);
        }catch(DataAccessException e){
            fail("DataAccessException should not be thrown");
        }catch(AccountNotActivatedException e){
            fail("AccountNotActivatedException should not be thrown");
        }
        assertNotNull(info);
        assertTrue(info.size() > 0);
    }
}
