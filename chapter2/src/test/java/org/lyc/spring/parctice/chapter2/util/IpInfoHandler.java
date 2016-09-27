package org.lyc.spring.parctice.chapter2.util;

import redis.clients.jedis.Jedis;

/**
 * Created by admin on 2016/9/26.
 */
public interface IpInfoHandler {
    public abstract void process(Long key,String [] info);

}
