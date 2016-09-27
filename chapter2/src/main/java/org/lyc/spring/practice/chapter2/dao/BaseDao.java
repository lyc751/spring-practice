package org.lyc.spring.practice.chapter2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * Created by admin on 2016/9/26.
 */
@Repository
public class BaseDao {

    @Autowired
    private JedisSentinelPool jedisSentinelPool;

    protected Jedis getJedis(){
        Jedis jedis = jedisSentinelPool.getResource();
        return jedis;
    }


}
