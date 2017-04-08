package com.example;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello GeekWeekly!";
    }

    @RequestMapping("/v1/")
    @ResponseBody
    String v1api() {
        return "Hello GeekWeekly V1!";
    }

    @RequestMapping("/v2/")
    @ResponseBody
    String v2api() {
        return "Hello GeekWeekly V2!";
    }

    @RequestMapping("/v3/")
    @ResponseBody
    String v3api() {
        return "Hello GeekWeekly V3!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}