package org.lyc.spring.practice.chapter1.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lyc.spring.practice.chapter1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lyc on 2016/8/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void hasMatchUser(){
        boolean b1 = userService.hasMatchUser("admin","123456");
        boolean b2 = userService.hasMatchUser("admin","0000");
        Assert.assertTrue(b1);
        Assert.assertTrue(!b2);
    }

    public void findUserByUserName(){
        User user = userService.findUserByUserName("admin");
        Assert.assertEquals(user.getUserName(),"admin");
    }
}
