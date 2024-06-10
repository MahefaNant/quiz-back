package com.dev.quiz.controller;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping
    public String init() {
        return "Hello Everyone !";
    }

    @GetMapping("/first/{name}")
    //@PreAuthorize("hasRole('ADMIN')")
    public String first(@PathVariable(value = "name" , required = false) String name) {
        if(name == null || name.isEmpty()) {
            name = " ? ";
        }
        return "Hi " + name ;
    }

}
