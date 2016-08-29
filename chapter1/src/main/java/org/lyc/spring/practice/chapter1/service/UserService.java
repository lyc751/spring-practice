package org.lyc.spring.practice.chapter1.service;

import org.lyc.spring.practice.chapter1.dao.LoginLogDao;
import org.lyc.spring.practice.chapter1.dao.UserDao;
import org.lyc.spring.practice.chapter1.domain.LoginLog;
import org.lyc.spring.practice.chapter1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2016/8/29.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;

    public User findUserByUserName(String userName){
        return userDao.findUserByUserName(userName);
    }

    public boolean hasMatchUser(String userName,String password){
        int matchCount = userDao.getMatchCount(userName,password);
        return matchCount > 0;
    }

    public void loginSuccess(User user){
        user.setCredits(user.getCredits()+5);
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
