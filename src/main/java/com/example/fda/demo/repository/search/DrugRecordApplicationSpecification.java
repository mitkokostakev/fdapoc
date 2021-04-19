package com.example.fda.demo.repository.search;

import com.example.fda.demo.model.entity.DrugRecordApplication;
import com.example.fda.demo.util.EnumUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Class defining {@link org.springframework.data.jpa.domain.Specification} for ConversionRate
 * entries
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class DrugRecordApplicationSpecification
    implements SearchSpecification<DrugRecordApplication> {

  private final transient List<SearchCriteria> list = new ArrayList<>();

  /**
   * Method for adding {@link SearchCriteria} to the list of all criterias for Search
   *
   * @param criteria instance of type {@link SearchCriteria}
   */
  @Override
  public void add(SearchCriteria criteria) {
    list.add(criteria);
  }

  /**
   * Method generating {@link Predicate}
   *
   * @param root The root object
   * @param query {@link CriteriaQuery} used when building the Predicate
   * @param builder {@link CriteriaBuilder} used when building the Predicate
   * @return {@link Predicate} populated from a list of search criterias
   */
  @Override
  public Predicate toPredicate(
      Root<DrugRecordApplication> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

    // create a new predicate list
    List<Predicate> predicates = new ArrayList<>();

    List<Order> orders = new ArrayList<>();

    // add add criteria to predicates
    for (SearchCriteria criteria : list) {
      Object value = criteria.getValue();
      Expression expression = root.get(criteria.getKey()).as(value.getClass());
      predicates.add(criteria.getOperation().computePredicate(builder, expression, value));

      if (!criteria.getKey().contains("deleted")) {
        orders.add(
            builder.asc(
                root.get(
                    EnumUtils.of(DrugRecordApplicationSearchField.class, criteria.getKey())
                        .getJsonName())));
      }
    }

    // Set query order by
    query.orderBy(orders);

    return builder.and(predicates.toArray(new Predicate[0]));
  }
}
