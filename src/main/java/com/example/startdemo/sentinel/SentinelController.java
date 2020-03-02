package com.example.startdemo.sentinel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xuweihang@qbb.com
 * @date 2020-02-29 14:12
 */
@Controller
public class SentinelController {


    @RequestMapping(value = "sentinel/test", method = RequestMethod.GET)
    @ResponseBody
    @Sentinel
    private String test() {
        return "aaaa";
    }
}
