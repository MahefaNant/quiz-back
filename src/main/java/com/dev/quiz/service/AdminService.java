package com.dev.quiz.service;

import com.dev.quiz.Model.MessageMod;
import com.dev.quiz.entity.Admin;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface AdminService {

    Admin add(Admin admin);

    @Async
    CompletableFuture<Admin> update(Admin admin, String newPassword , String retapePassword);

    @Async
    CompletableFuture<MessageMod> login(Admin admin);

}
