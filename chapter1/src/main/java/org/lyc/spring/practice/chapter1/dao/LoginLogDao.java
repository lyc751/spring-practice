package org.lyc.spring.practice.chapter1.dao;

import org.lyc.spring.practice.chapter1.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2016/8/24.
 */
@Repository
public class LoginLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertLoginLog(LoginLog log){
        String sql = "insert into t_login_log (user_id,ip,login_datetime) values (?,?,?)";
        jdbcTemplate.update(sql,new Object[]{log.getUserId(),log.getIp(),log.getLoginDate()});
    }
}
