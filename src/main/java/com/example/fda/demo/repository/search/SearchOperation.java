package com.example.fda.demo.repository.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

/** Enum defining generic SearchOperations */
@RequiredArgsConstructor
public enum SearchOperation implements SearchOperationStrategy<Object> {
  GREATER_THAN((builder, root, value) -> builder.greaterThan(root, value.toString())),
  LESS_THAN((builder, root, value) -> builder.lessThan(root, value.toString())),
  GREATER_THAN_EQUAL(
      (builder, root, value) -> builder.greaterThanOrEqualTo(root, value.toString())),
  LESS_THAN_EQUAL((builder, root, value) -> builder.lessThanOrEqualTo(root, value.toString())),
  NOT_EQUAL((builder, root, value) -> builder.notEqual(root, value)),
  EQUAL((builder, root, value) -> builder.equal(root, value)),
  MATCH(
      (builder, root, value) ->
          builder.like(builder.lower(root), "%" + value.toString().toLowerCase() + "%")),
  MATCH_START(
      (builder, root, value) ->
          builder.like(builder.lower(root), value.toString().toLowerCase() + "%")),
  MATCH_END(
      (builder, root, value) ->
          builder.like(builder.lower(root), "%" + value.toString().toLowerCase())),
  IN((builder, root, value) -> builder.in(root).value(value)),
  NOT_IN((builder, root, value) -> builder.not(root).in(value)),

  GREATER_THAN_OR_NULL(
      (builder, root, value) ->
          builder.or(builder.greaterThan(root, value.toString()), builder.isNull(root))),
  LESS_THAN_OR_NULL(
      (builder, root, value) ->
          builder.or(builder.lessThan(root, value.toString()), builder.isNull(root))),
  GREATER_THAN_EQUAL_OR_NULL(
      (builder, root, value) ->
          builder.or(builder.greaterThanOrEqualTo(root, value.toString()), builder.isNull(root))),
  LESS_THAN_EQUAL_OR_NULL(
      (builder, root, value) ->
          builder.or(builder.lessThanOrEqualTo(root, value.toString()), builder.isNull(root))),
  NOT_EQUAL_OR_NULL(
      (builder, root, value) -> builder.or(builder.notEqual(root, value), builder.isNull(root))),
  EQUAL_OR_NULL(
      (builder, root, value) -> builder.or(builder.equal(root, value), builder.isNull(root))),
  MATCH_OR_NULL(
      (builder, root, value) ->
          builder.or(
              builder.like(builder.lower(root), "%" + value.toString().toLowerCase() + "%"),
              builder.isNull(root))),
  MATCH_START_OR_NULL(
      (builder, root, value) ->
          builder.or(
              builder.like(builder.lower(root), value.toString().toLowerCase() + "%"),
              builder.isNull(root))),
  MATCH_END_OR_NULL(
      (builder, root, value) ->
          builder.or(
              builder.like(builder.lower(root), "%" + value.toString().toLowerCase()),
              builder.isNull(root)));

  private final SearchOperationStrategy searchOperationStrategy;

  @Override
  public Predicate computePredicate(CriteriaBuilder builder, Expression root, Object value) {
    return searchOperationStrategy.computePredicate(builder, root, value);
  }
}
