package com.example.fda.demo.repository.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum defining on which property which type of {@link SearchOperation} should be assigned when
 * building {@link org.springframework.data.jpa.domain.Specification} for ConversionRate entries
 */
@Getter
@AllArgsConstructor
public enum DrugRecordApplicationSearchField implements SearchField {
  APPLICATION_NUMBER("applicationNumber", SearchOperation.EQUAL),
  MANUFACTURER_NAME("manufacturerName", SearchOperation.EQUAL),
  SUBSTANCE_NAME("substanceName", SearchOperation.EQUAL),

  // Non Request searching fields
  DELETED("deleted", SearchOperation.EQUAL);

  private String jsonName;
  private SearchOperationStrategy searchOperation;
}
