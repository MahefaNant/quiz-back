package com.dev.quiz.controller.admin;

import com.dev.quiz.Model.MessageMod;
import com.dev.quiz.entity.Admin;
import com.dev.quiz.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;


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
    @PostMapping("/add")
    public ResponseEntity<?> addAdmin(@RequestBody Admin admin) {
        try {
            Admin savedAdmin = adminService.add(admin);
            return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        CompletableFuture<MessageMod> message = adminService.login(admin);
        MessageMod mess = message.join();
        return new ResponseEntity<>(mess , HttpStatus.ACCEPTED);
    }
}
