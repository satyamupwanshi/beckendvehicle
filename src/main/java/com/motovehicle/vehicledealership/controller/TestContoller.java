package com.motovehicle.vehicledealership.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContoller {

    @GetMapping("/health")
    public String healthCheck() {
        return "App is running!";
    }
}

