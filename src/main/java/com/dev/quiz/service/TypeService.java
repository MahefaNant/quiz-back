package com.dev.quiz.service;

import com.dev.quiz.entity.Type;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TypeService {

    Type add(Type type);
    CompletableFuture<Type> update(Type type);

    CompletableFuture<List<Type>> all();

    CompletableFuture<Type> getById(int id);

}
