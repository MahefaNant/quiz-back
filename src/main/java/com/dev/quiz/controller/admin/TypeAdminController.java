package com.dev.quiz.controller.admin;

import com.dev.quiz.entity.Type;
import com.dev.quiz.service.TypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/admin/type")
public class TypeAdminController {

    private final TypeService typeService;

    public TypeAdminController(TypeService typeService) {
        this.typeService = typeService;
    }


    @PostMapping("/add")
    public Type add(@RequestBody Type type) {
        return typeService.add(type);
    }

    @GetMapping
    public CompletableFuture<List<Type>> all() {
        return typeService.all();
    }

    @GetMapping("/get-{id}")
    public CompletableFuture<Type> byId(@PathVariable int id) {
        return typeService.getById(id);
    }
}
