package com.dev.quiz.repository;

import com.dev.quiz.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepo extends JpaRepository<Response , Integer> {
}
