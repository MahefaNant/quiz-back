package com.dev.quiz.controller.admin;

import com.dev.quiz.Model.MessageDetails;
import com.dev.quiz.Model.MessageMod;
import com.dev.quiz.entity.Admin;
import com.dev.quiz.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;


@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /*
        add new admin
     */

    @GetMapping("/test")
    public String test() {
        return "azeaz";
    }

    @PostMapping("/add")
    public Admin addAdmin(@RequestBody Admin admin) {
        return  adminService.add(admin);
    }

    /*
        update admin
     */
    @PostMapping("/update")
    public ResponseEntity<?> updateAdmin(@RequestBody Admin admin ,
                                         @RequestParam String newPassword ,
                                         @RequestParam String retapePassword ) {
        try {
            Admin savedAdmin = adminService.update(admin , newPassword, retapePassword).join();
            return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
        method for sign-in "admin"
     */
    @PostMapping("/login")
    public CompletableFuture<MessageDetails> login(@RequestBody Admin admin) {
        return adminService.login(admin);
    }

    @GetMapping("/get-{id}")
    public CompletableFuture<Admin> getAdmin(@PathVariable int id) {
        return adminService.getAdmin(id);
    }



}
