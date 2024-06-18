package com.dev.quiz.controller.admin;

import com.dev.quiz.Model.searchCriteria.QuestionSearchCriteria;
import com.dev.quiz.entity.Question;
import com.dev.quiz.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

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

    // pageable : ?page=0&size=2&sort=idEx,desc
    @GetMapping
    public CompletableFuture<Page<Question>> questions(QuestionSearchCriteria criteria, Pageable pageable) {
        return questionService.questions(criteria, pageable);
    }
}
