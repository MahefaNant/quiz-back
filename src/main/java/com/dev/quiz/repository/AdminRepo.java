package com.dev.quiz.repository;

import com.dev.quiz.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin , Integer> {

    Optional<Admin> findFirstBy();
    Optional<Admin> findFirstById(int id);

}
