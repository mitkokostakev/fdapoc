package com.example.fda.demo.repository.search;

import com.example.fda.demo.util.EnumUtils;
import com.google.common.collect.Lists;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

/**
 * Base Builder class for building {@link SearchSpecification}
 *
 * @param <E> instance of class which extends {@link Enum} and implements {@link SearchField}
 * @param <S> instance of entity class for which the builder is going to be used
 */
@Slf4j
@RequiredArgsConstructor
public class BaseSpecificationBuilder<E extends Enum<E> & SearchField, S> {

  /** Field containing the Enum class type */
  private final Class<E> type;

  /**
   * Method containing the implementation for populating {@link SearchSpecification}
   *
   * @param object entity object for which the Specification would be populated
   * @param querySpecification the query specification instance for which the SearchCriterias are
   *     going to be added
   * @throws IllegalAccessException This type of Exception may be thrown when accessing
   *     field.get(object)
   */
  protected void populateSearchCriterias(Object object, SearchSpecification<S> querySpecification)
      throws IllegalAccessException {
    Field[] declaredFields = object.getClass().getDeclaredFields();
    List<Field> fields = Lists.newArrayList(declaredFields);
    fields.sort(Comparator.comparing(Field::getName));

    for (Field field : fields) {

      field.setAccessible(true); // if you want to modify private fields
      Object fieldValue = field.get(object);
      String fieldName = field.getName();
      log.debug(
          "Trying to add SearchCriteria for field ===== {} ======, field.get(object) resulted in {}",
          field.getName(),
          fieldValue);

      E currentSearchField = null;

      try {
        currentSearchField = EnumUtils.of(type, fieldName);
      } catch (java.lang.IllegalArgumentException e) {
        log.debug("No SearchField found in {} for : {}", type, fieldName);
      }

      if (ObjectUtils.isEmpty(fieldValue)
          || ObjectUtils.isEmpty(currentSearchField)
          || ObjectUtils.isEmpty(currentSearchField.getSearchOperation())) {
        log.debug(
            "Skipping adding of SearchCriteria for fieldValue {} and currentSearchField : {}",
            fieldValue,
            currentSearchField);
        continue;
      }
      querySpecification.add(
          new SearchCriteria(
              currentSearchField.getJsonName(),
              fieldValue,
              currentSearchField.getSearchOperation()));
    }
  }
}
