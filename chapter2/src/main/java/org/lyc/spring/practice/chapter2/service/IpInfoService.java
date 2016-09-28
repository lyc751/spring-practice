package org.lyc.spring.practice.chapter2.service;

import org.lyc.spring.practice.chapter2.common.CommonUtil;
import org.lyc.spring.practice.chapter2.dao.IpInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2016/9/27.
 */
@Service
public class IpInfoService {

    @Autowired
    private IpInfoDao infoDao;

    public String getIpInfo(String ip){
        Long ipid = CommonUtil.ipToLong(ip);
        return infoDao.getIpInfo(ipid);
    }
}
