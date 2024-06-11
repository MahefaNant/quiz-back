package com.dev.quiz.service;

import com.dev.quiz.Model.searchCriteria.QuestionSearchCriteria;
import com.dev.quiz.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface QuestionService {

    Question add(Question question);

    CompletableFuture<Question> update(Question question);

    CompletableFuture<Question> getById(int id);

    CompletableFuture<Page<Question>> questions(QuestionSearchCriteria criteria, Pageable pageable);

}
