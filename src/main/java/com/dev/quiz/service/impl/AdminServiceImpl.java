package com.dev.quiz.service.impl;

import com.dev.quiz.Model.MessageMod;
import com.dev.quiz.entity.Admin;
import com.dev.quiz.repository.AdminRepo;
import com.dev.quiz.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepo adminRepo;

    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public Admin add(Admin admin) {
        try {
            if(admin.getMail() == null || admin.getMail().trim().isEmpty() ||
                    (admin.getPassword() == null || admin.getPassword().trim().isEmpty()) ) {
                throw new Exception("formulaire obligatoire");
            }
            admin.setPasswordEncode(admin.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        try {
            return adminRepo.save(admin);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save admin or existing mail", e);
        }

    }

    @Override
    public CompletableFuture<Admin> update(Admin admin, String newPassword , String retapePassword) {
        try {
            BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
            if(!newPassword.equals(retapePassword)) throw new Exception("Mots de passe non Identique !");
            Admin oldAdmin = adminRepo.findFirstBy().orElseThrow(() -> new Exception("Admin non trouvé"));
            if(!crypt.matches(admin.getPassword() , oldAdmin.getPassword())) throw new Exception("mots de passe Invalide!");
            oldAdmin.setPasswordEncode(retapePassword);
            return CompletableFuture.completedFuture(adminRepo.save(oldAdmin));
        } catch (Exception e) {
            throw  new RuntimeException(e.getMessage());
        }
    }

    @Override
    public CompletableFuture<MessageMod> login(Admin admin) {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Admin oldAdmin = adminRepo.findFirstBy().orElseThrow(() -> new Exception("Admin non trouvé"));

            MessageMod messageMod = new MessageMod();
            messageMod.num = 1;
            if( !passwordEncoder.matches(admin.getPassword() , oldAdmin.getPassword()) || !oldAdmin.getMail().equalsIgnoreCase(admin.getMail())) {
                messageMod.num = 0;
                messageMod.message = "mail ou mots de passe Invalide";
            }
            else messageMod.message = "Authentification avec succé";
            return CompletableFuture.completedFuture(messageMod);
        } catch (Exception e) {
            MessageMod messageMod = new MessageMod();
            messageMod.num = 0;
            messageMod.message = "Server error ! Contact the administrator";
            return CompletableFuture.completedFuture(messageMod);
        }
    }
}
