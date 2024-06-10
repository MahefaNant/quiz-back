package com.dev.quiz.service.impl.specification;

import com.dev.quiz.Model.SearchCriteria;
import com.dev.quiz.entity.Question;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class QuestionSpecification implements Specification<Question> {

    private SearchCriteria criteria;

    public QuestionSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case ">":
                return builder.greaterThan(root.get(criteria.getKey()) , criteria.getValue().toString());
        }
        return null;
    }
}
