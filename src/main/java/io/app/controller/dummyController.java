package io.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dummyController {
    @GetMapping("/")
    public String dummy(){
        return "Hello";
    }
}
