package com.dev.quiz.repository;

import com.dev.quiz.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate , Integer> {
    Optional<Candidate> findFirstBy();
    Optional<Candidate> findFirstByMailContainingIgnoreCase(String mail);
}
