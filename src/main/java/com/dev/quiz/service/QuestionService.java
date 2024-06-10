package com.dev.quiz.service;

import com.dev.quiz.Model.SearchCriteria;
import com.dev.quiz.entity.Question;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface QuestionService {

    Question add(Question question);

    CompletableFuture<List<Question>> all();
    CompletableFuture<Question> getById(int id);

    CompletableFuture<List<Question>> advanceSearch(List<SearchCriteria> criteria);

}
