package com.example.fda.demo.service;

import com.example.fda.demo.exception.DrugRecordApplicationNotFoundException;
import com.example.fda.demo.integration.fda.model.OpenFDAResponse;
import com.example.fda.demo.model.entity.DrugRecordApplication;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

/** Interface defining Service layer for DrugRecordApplication entries */
public interface OpenFDAService {

  /**
   * This method provides integration with OpenFDA API
   *
   * @param manufacturer mandatory field parameter used for search
   * @param brandName optional parameter for fine graining the search using brand name
   * @param page input parameter for choosing which page number to display
   * @param pageSize input parameter defining how many records to return per page
   * @return instance of {@link OpenFDAResponse} containing the result
   */
  ResponseEntity<OpenFDAResponse> findSubmittedForApproval(
      String manufacturer, String brandName, Integer page, Integer pageSize);

  /**
   * Method for finding a {@link DrugRecordApplication} entry by Id of type {@link Long}
   *
   * @param id the unique identifier of an entry
   * @return an instance of {@link DrugRecordApplication}
   * @throws DrugRecordApplicationNotFoundException Method may throw this exception in case no entry
   *     was found
   */
  DrugRecordApplication findById(Long id) throws DrugRecordApplicationNotFoundException;

  /**
   * Method providing an interface to find specific DrugRecordApplication entry
   *
   * @param fields map containing values which is going to be used when building the query
   * @return instance of {@link DrugRecordApplication} containing the result
   * @throws DrugRecordApplicationNotFoundException Method may throw this exception in case no entry
   *     was found
   * @throws IllegalAccessException Method may throw this exception
   */
  DrugRecordApplication findByQuery(Map<Object, Object> fields, Integer page, Integer size)
      throws DrugRecordApplicationNotFoundException, IllegalAccessException;

  /**
   * Method providing create operation for adding new entries
   *
   * @param drugRecordApplication the object to persist
   * @return the saved object of type {@link DrugRecordApplication}
   */
  DrugRecordApplication create(DrugRecordApplication drugRecordApplication);

  /**
   * Method providing a soft DELETE operation
   *
   * @param applicationNumber the unique identifier of an entry
   * @throws DrugRecordApplicationNotFoundException Method may throw this exception in case no entry
   *     was found
   */
  void delete(String applicationNumber) throws DrugRecordApplicationNotFoundException;

  /**
   * Method proviging functionality to search for all entries
   *
   * @return {@link List} containing all entries for ${@link DrugRecordApplication}
   */
  List<DrugRecordApplication> findAll();

  /**
   * Method proviging functionality to search for all entries which were softly deleted
   *
   * @return {@link List} containing all entries for ${@link DrugRecordApplication}
   */
  List<DrugRecordApplication> findDeleted();
}
