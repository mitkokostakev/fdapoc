package com.example.fda.demo.repository.search;

/**
 * Interface defining that an Enum implementing the interface would have a {@link SearchOperation}
 * on every value.
 *
 * @param <T> generic type for the SearchOperationStrategy
 */
public interface SearchField<T> {
  SearchOperationStrategy<T> getSearchOperation();

  String getJsonName();
}
