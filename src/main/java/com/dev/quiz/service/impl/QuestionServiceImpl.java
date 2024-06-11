package com.dev.quiz.service.impl;

import com.dev.quiz.Model.SearchCriteria;
import com.dev.quiz.Model.searchCriteria.QuestionSearchCriteria;
import com.dev.quiz.entity.Question;
import com.dev.quiz.repository.QuestionRepo;
import com.dev.quiz.service.QuestionService;
import com.dev.quiz.service.impl.specification.GlobalSpecification;
import com.dev.quiz.service.impl.specification.QuestionSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepo;

    public QuestionServiceImpl(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    @Override
    public Question add(Question question) {
        if(question.getQuestion() == null || question.getQuestion().trim().isEmpty() ||
                question.getType().getId() <=0) {
            throw new IllegalArgumentException("form required");
        }
        return questionRepo.save(question);
    }

    @Override
    public CompletableFuture<List<Question>> all() {
        return null;
    }

    @Override
    public CompletableFuture<Question> getById(int id) {
        return null;
    }

    @Override
    public CompletableFuture<Page<Question>> advanceSearch(QuestionSearchCriteria criteria , Pageable pageable) {

        return CompletableFuture.supplyAsync(() -> {

            Page<Question> questions = questionRepo.findAll(QuestionSpecification.getQuestions(criteria) , pageable);
            if(questions.isEmpty()) throw new EntityNotFoundException("Empty List");
            return questions;
        });
    }
}
