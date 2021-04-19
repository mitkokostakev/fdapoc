package com.example.fda.demo.repository.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

/**
 * Functional interface for defining SearchOperationStrategy
 *
 * @param <T> generic type
 */
@FunctionalInterface
public interface SearchOperationStrategy<T> {
  Predicate computePredicate(CriteriaBuilder builder, Expression<T> root, T value);
}
