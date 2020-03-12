package com.example.startdemo.sentinel;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xuweihang@qbb.com
 * @date 2020-02-29 14:12
 */
@Controller
public class SentinelController {

    private static Gson gson = new Gson();

    @RequestMapping(value = "sentinel/test", method = RequestMethod.GET)
    @ResponseBody
    @Sentinel
    public String test() {
        return "aaaa";
    }

    public static void main(String[] args) {

    }
}
