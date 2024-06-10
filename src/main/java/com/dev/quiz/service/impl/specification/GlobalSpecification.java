package com.dev.quiz.service.impl.specification;

import com.dev.quiz.Model.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class GlobalSpecification implements Specification<Object> {

    private final SearchCriteria criteria;

    public GlobalSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Object> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case ">":
                return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case ">=":
                return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            case "<":
                return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case "<=":
                return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            case ":":
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
                } else {
                    return builder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            case "=" :
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    builder.equal(root.get(criteria.getKey()), criteria.getValue());
                } else {
                    builder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            case "!=":
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return builder.notLike(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
                } else {
                    return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
                }
            case "true":
                return builder.isTrue(root.get(criteria.getKey()).as(Boolean.class));
            case "false":
                return builder.isFalse(root.get(criteria.getKey()).as(Boolean.class));
            case "null":
                return builder.isNull(root.get(criteria.getKey()));
            case "notnull":
                return builder.isNotNull(root.get(criteria.getKey()));
            default:
                return null;
        }
    }
}
