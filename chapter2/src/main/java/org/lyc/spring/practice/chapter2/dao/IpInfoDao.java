package org.lyc.spring.practice.chapter2.dao;

import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

/**
 * Created by admin on 2016/9/23.
 */
@Repository
public class IpInfoDao extends BaseDao{

    public String getIpInfo(Long ipid){
        Set<Tuple> ips;
        String IpInfo = "";
        try (Jedis jedis = getJedis()){
            ips = jedis.zrangeByScoreWithScores("chapter2.ipinfo_z",new Double(ipid),new Double(4294967295L),0,1);
        }
        while (ips.iterator().hasNext()) {
            Tuple next = ips.iterator().next();
            IpInfo = next.getElement();
        }
        return IpInfo;
    }
}
