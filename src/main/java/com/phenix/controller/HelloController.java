package com.phenix.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
    @RequestMapping("/")
    public String index() {
        return "Hello Spring Boot 2.0, and CI Docker!";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World in docker, good merlin!";
    }
}