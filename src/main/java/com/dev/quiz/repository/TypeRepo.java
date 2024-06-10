package com.dev.quiz.repository;

import com.dev.quiz.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepo extends JpaRepository<Type , Integer> {
    Optional<Type> findFirstById(int id);
}
