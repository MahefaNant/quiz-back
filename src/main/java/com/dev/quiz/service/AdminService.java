package com.dev.quiz.service;

import com.dev.quiz.Model.MessageDetails;
import com.dev.quiz.entity.Admin;

import java.util.concurrent.CompletableFuture;

public interface AdminService {

    Admin add(Admin admin);

    CompletableFuture<Admin> update(Admin admin, String newPassword , String retapePassword);

    CompletableFuture<MessageDetails> login(Admin admin);

    CompletableFuture<Admin> getAdmin(int id);

}
