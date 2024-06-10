package com.dev.quiz.service.impl;

import com.dev.quiz.Model.MessageDetails;
import com.dev.quiz.Model.MessageMod;
import com.dev.quiz.entity.Admin;
import com.dev.quiz.repository.AdminRepo;
import com.dev.quiz.service.AdminService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepo adminRepo;

    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public Admin add(Admin admin) {
        if(admin.getMail() == null || admin.getMail().trim().isEmpty() ||
                (admin.getPassword() == null || admin.getPassword().trim().isEmpty()) ) {
            throw new IllegalArgumentException("form required");
        }
        admin.setPasswordEncode(admin.getPassword());

        return adminRepo.save(admin);
    }

    @Override
    public CompletableFuture<Admin> update(Admin admin, String newPassword , String retapePassword) {

        return CompletableFuture.supplyAsync(() -> {
            BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
            if(!newPassword.equals(retapePassword)) throw new IllegalArgumentException("Password isn't th same");
            Admin oldAdmin = adminRepo.findFirstBy().orElseThrow(() -> new EntityNotFoundException("Admin not found"));
            if(!crypt.matches(admin.getPassword() , oldAdmin.getPassword())) throw new IllegalArgumentException("Invalid password!");
            oldAdmin.setPasswordEncode(retapePassword);
            return adminRepo.save(oldAdmin);
        });
    }

    @Override
    public CompletableFuture<MessageDetails> login(Admin admin) {

        return CompletableFuture.supplyAsync(() -> {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Admin oldAdmin = adminRepo.findFirstBy().orElseThrow(() -> new EntityNotFoundException("Incorrect Mail or password"));
            if( !passwordEncoder.matches(admin.getPassword() , oldAdmin.getPassword()) || !oldAdmin.getMail().equalsIgnoreCase(admin.getMail())) {
                throw new EntityNotFoundException("Incorrect Mail or password");
            }
            else {
                MessageDetails mess = new MessageDetails(oldAdmin.getId(), "Login successfully", "");
                return mess;
            }
        });
    }

    @Override
    public CompletableFuture<Admin> getAdmin(int id) {
        return CompletableFuture.supplyAsync(() -> adminRepo.findFirstById(id).orElseThrow(() -> new EntityNotFoundException("Admin not found!")));
    }





}
