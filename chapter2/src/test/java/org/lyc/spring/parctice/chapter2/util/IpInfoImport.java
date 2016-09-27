package org.lyc.spring.parctice.chapter2.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2016/9/26.
 */
public class IpInfoImport {

    private JedisSentinelPool jedisSentinelPool;

    public IpInfoImport() {
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("172.16.57.207:26380");
        sentinels.add("172.16.57.251:26381");
        String masterName = "mymaster";
        String password = "Tg@Rs12fg";
        jedisSentinelPool = new JedisSentinelPool(masterName,sentinels,password);
    }

    public static void main(String[] args) {
        IpInfoImport ipInfoImport = new IpInfoImport();
        ipInfoImport.parseIp();
    }


    public void parseIp(){
        try (final Jedis jedis = jedisSentinelPool.getResource()){
            jedis.select(4);
            IpParser ipParser = new IpParser(new IpInfoHandler() {
                @Override
                public void process(Long key, String[] info) {
                    IpInfoObj ipInfoObj = new IpInfoObj(key,IpParser.longToIP(key),info);
                    String ipInfoStr = JSON.toJSONString(ipInfoObj);
//                System.out.println(String.valueOf(key)+"\t"+ipInfoStr);
//                    printWriter.println(String.valueOf(key)+"\t"+ipInfoStr);
                    jedis.zadd("chapter2.ipinfo_z",new Double(key),ipInfoStr);
                }
            });
            ipParser.load("D:/Tmp Files/mydata4vipday2.datx");
            ipParser.getAllIp();
        }


    }
}

class IpInfoObj{
    public Long IPID;
    public String IP;
    public String Country;
    public String ShortProvince;
    public String ShortCity;
    public String Organization;
    public String TSPAlsaName;
    public String latitude;
    public String longitude;
    public String Timezone1;
    public String Timezone2;
    public String AreaID;
    public String InternationalPhoneCode;
    public String CountryCode;
    public String ContinentsCode;

    public IpInfoObj(Long ipid,String ip,String [] IpInfo) {
        this.IPID=ipid;
        this.IP=ip;
        this.Country = IpInfo[0];
        this.ShortProvince = IpInfo[1];
        this.ShortCity = IpInfo[2];
        this.Organization = IpInfo[3];
        this.TSPAlsaName = IpInfo[4];
        this.latitude = IpInfo[5];
        this.longitude = IpInfo[6];
        this.Timezone1 = IpInfo[7];
        this.Timezone2 = IpInfo[8];
        this.AreaID = IpInfo[9];
        this.InternationalPhoneCode = IpInfo[10];
        this.CountryCode = IpInfo[11];
        this.ContinentsCode = IpInfo[12];
    }


}