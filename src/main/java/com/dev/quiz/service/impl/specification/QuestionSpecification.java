package com.dev.quiz.service.impl.specification;

import com.dev.quiz.Model.searchCriteria.QuestionSearchCriteria;
import com.dev.quiz.entity.Question;
import com.dev.quiz.entity.Type;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class QuestionSpecification {

    public static Specification<Question> getQuestions(QuestionSearchCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getIdType() != null) {
                Join<Question , Type> typeJoin = root.join("type");
                predicates.add(criteriaBuilder.equal(typeJoin.get("id"), criteria.getIdType()));
            }

            if (criteria.getQuestion() != null && !criteria.getQuestion().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("question"), "%" + criteria.getQuestion() + "%"));
            }

            if (criteria.getIsInProgress() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isInProgres"), criteria.getIsInProgress()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
