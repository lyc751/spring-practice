package org.lyc.spring.practice.chapter2.controller;

import org.lyc.spring.practice.chapter2.common.CommonUtil;
import org.lyc.spring.practice.chapter2.service.IpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2016/9/28.
 */
@RestController("/ip")
public class IpController {

    @Autowired
    private IpInfoService ipInfoService;

    @RequestMapping(value = "/getipinfo",method = {RequestMethod.POST,RequestMethod.GET})
    public String getIpInfo(@RequestParam("ip") String ip){
        if (!CommonUtil.isIp(ip)){
            return "{\"Bad Ip!\"}";
        }else {
            return ipInfoService.getIpInfo(ip);
        }
    }
}
