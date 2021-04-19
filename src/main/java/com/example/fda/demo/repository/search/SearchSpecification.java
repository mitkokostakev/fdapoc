package com.example.fda.demo.repository.search;

import java.io.Serializable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Interface defining Specifications for Search
 *
 * @param <T> generic type
 */
public interface SearchSpecification<T> extends Specification<T>, Serializable {
  void add(SearchCriteria criteria);
}
