package com.example.startdemo.aspect;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/c001")
public class Mytestas {

    @RequestMapping("/f01")
    @ItestAspect(v = "AA")
    public void aaa(@RequestBody TestM t) {
        String a = t.getA();
        System.out.println(a);
    }
}
