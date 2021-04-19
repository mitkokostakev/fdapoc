package com.example.fda.demo.repository.search;

import com.example.fda.demo.model.entity.DrugRecordApplication;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/** Builder class for SpecialAgreementsRatingSpecification */
@Component
public class DrugRecordApplicationSpecificationBuilder
    extends BaseSpecificationBuilder<DrugRecordApplicationSearchField, DrugRecordApplication> {

  public DrugRecordApplicationSpecificationBuilder() {
    super(DrugRecordApplicationSearchField.class);
  }

  /**
   * Method for building {@link Specification} from DrugRecordApplication object
   *
   * @param object Input object of type {@link DrugRecordApplication}
   * @return instance of {@link Specification}
   * @throws IllegalAccessException exception which may occur
   */
  public Specification<DrugRecordApplication> buildFrom(DrugRecordApplication object)
      throws IllegalAccessException {
    SearchSpecification<DrugRecordApplication> querySpecification =
        new DrugRecordApplicationSpecification();
    populateSearchCriterias(object, querySpecification);
    return querySpecification;
  }
}
