package com.dev.quiz.repository;

import com.dev.quiz.entity.ResponseCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseCandidateRepo extends JpaRepository<ResponseCandidate , Integer> {
}
