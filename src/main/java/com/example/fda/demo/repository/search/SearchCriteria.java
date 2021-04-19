package com.example.fda.demo.repository.search;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {
  private String key;
  private Object value;
  private SearchOperationStrategy operation;
}
