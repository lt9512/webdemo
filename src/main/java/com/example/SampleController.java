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

    @RequestMapping("/service/")
    @ResponseBody
    String awesomeService() {
        return "Some awesome service...!";
    }

    @RequestMapping("/service2/")
    @ResponseBody
    String awesomeService2() {
        return String.valueOf(add(40, 2));
    }

    private int add (int x, int y) {
        return x+y;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}