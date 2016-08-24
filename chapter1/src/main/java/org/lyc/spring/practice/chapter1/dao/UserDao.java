package org.lyc.spring.practice.chapter1.dao;

import org.lyc.spring.practice.chapter1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lyc on 2016/8/24.
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String userName,String password){
        String sql = "Select count(1) from sampledb.t_user where user_name = ? and passoword = ?";
        return jdbcTemplate.queryForInt(sql,new Object[]{userName,password});
    }

    public User findUserByUserName(String userName){
        String sql = "Select user_id,user_name,credits from sampledb.t_user where user_name = ?";
        final User user = new User();
        jdbcTemplate.query(sql, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user){
        String sql = "update sampledb.t_user t set t.last_visit=?,t.last_ip=?,t.credits=? where t.user_id =?";
        jdbcTemplate.update(sql,new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId()});
    }
}
