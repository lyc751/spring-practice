package org.lyc.spring.practice.chapter2.controller;

import org.lyc.spring.practice.chapter2.service.IpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2016/9/28.
 */
@RestController
public class IpController {

    @Autowired
    private IpInfoService ipInfoService;


}
