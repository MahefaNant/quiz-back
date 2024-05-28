package com.dev.quiz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/global")
public class GlobalController {

    @GetMapping("/first/{name}")
    public String first(@PathVariable(value = "name" , required = false) String name) {
        if(name == null || name.isEmpty()) {
            name = " ? ";
        }
        return "Hi " + name ;
    }

}
