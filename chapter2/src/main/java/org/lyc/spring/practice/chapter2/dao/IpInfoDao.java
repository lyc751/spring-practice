package org.lyc.spring.practice.chapter2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by admin on 2016/9/23.
 */
@Repository
public class IpInfoDao extends BaseDao{

    private int dbNum=0;

    public int getDbNum() {
        return dbNum;
    }

    public void setDbNum(int dbNum) {
        this.dbNum = dbNum;
    }

    public String getIpInfo(Long ipid){
        Set<Tuple> ips;
        String IpInfo = "";
        try (Jedis jedis = getJedis()){
            jedis.select(dbNum);
            ips = jedis.zrangeByScoreWithScores("chapter2.ipinfo_z",new Double(ipid),new Double(4294967295L),0,1);
        }
        Iterator<Tuple> iterator = ips.iterator();
        while (iterator.hasNext()) {
            Tuple next = iterator.next();
            IpInfo = next.getElement();
        }
        return IpInfo;
    }
}
