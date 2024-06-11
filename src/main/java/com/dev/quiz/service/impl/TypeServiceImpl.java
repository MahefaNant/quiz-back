package com.dev.quiz.service.impl;

import com.dev.quiz.entity.Type;
import com.dev.quiz.repository.TypeRepo;
import com.dev.quiz.service.TypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepo typeRepo;

    public TypeServiceImpl(TypeRepo typeRepo) {
        this.typeRepo = typeRepo;
    }

    @Override
    public Type add(Type type) {
        if(type.getName() == null || type.getName().trim().isEmpty() ||
                type.getDescription() == null || type.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("form required");
        }
        return typeRepo.save(type);
    }

    @Override
    public CompletableFuture<Type> update(Type type) {
        return CompletableFuture.supplyAsync(() -> {
           if(type.getName() == null || type.getDescription() == null
           || type.getName().trim().isEmpty() || type.getDescription().trim().isEmpty())
               throw new IllegalArgumentException("Invalid data");

           Type T = typeRepo.findFirstById(type.getId()).orElseThrow(() -> new EntityNotFoundException("type not found"));
            T.setName(type.getName());
            T.setDescription(type.getDescription());
            return typeRepo.save(T);
        });
    }

    @Override
    public CompletableFuture<List<Type>> all() {
        return CompletableFuture.supplyAsync(() -> {
            List<Type> types = typeRepo.findAll();
            if(types.isEmpty()) throw new EntityNotFoundException("Empty List");
            return types;
        });
    }

    @Override
    public CompletableFuture<Type> getById(int id) {
        return CompletableFuture.supplyAsync(() ->
            typeRepo.findFirstById(id).orElseThrow(() -> new EntityNotFoundException("type not found!"))
        );
    }
}
