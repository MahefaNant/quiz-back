package com.dev.quiz.repository;

import com.dev.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepo extends JpaRepository<Question , Integer> , JpaSpecificationExecutor<Question> {

    Optional<Question> findFirstById(int id);
}
