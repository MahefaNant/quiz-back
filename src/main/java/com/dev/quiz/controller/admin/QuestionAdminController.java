package com.dev.quiz.controller.admin;

import com.dev.quiz.Model.SearchCriteria;
import com.dev.quiz.entity.Question;
import com.dev.quiz.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/question")
public class QuestionAdminController {

    private final QuestionService questionService;

    public QuestionAdminController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public Question add(@RequestBody Question question) {
        return questionService.add(question);
    }

    @GetMapping("/search")
    public CompletableFuture<List<Question>> searchQuestion(@RequestParam(value = "criteria") List<String> criteria) {
        List<SearchCriteria> searchCriteria = criteria.stream()
                .map(cr -> {
                    String[] parts = cr.split(",");
                    return new SearchCriteria(parts[0] , parts[1] , parts[2]);
                })
                .collect(Collectors.toList());
        return questionService.advanceSearch(searchCriteria);
    }
}
