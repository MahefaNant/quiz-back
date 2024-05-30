package com.dev.quiz.service;

import com.dev.quiz.Model.MessageMod;
import com.dev.quiz.entity.Candidate;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface CandidateService {

    Candidate add(Candidate candidate , String retapePassword);

    @Async
    CompletableFuture<Candidate> update(Candidate candidate, String newPassword , String retapePassword);

    @Async
    CompletableFuture<MessageMod> login(Candidate candidate);

}
