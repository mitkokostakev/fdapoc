package com.example.fda.demo.controller;

import com.example.fda.demo.exception.DrugRecordApplicationNotFoundException;
import com.example.fda.demo.integration.fda.model.OpenFDAResponse;
import com.example.fda.demo.model.dto.DrugRecordApplicationCreateRequest;
import com.example.fda.demo.model.dto.DrugRecordApplicationResource;
import com.example.fda.demo.model.mapper.DrugRecordApplicationMapper;
import com.example.fda.demo.service.OpenFDAService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FDAController implements OpenFDAAPI {

  private final OpenFDAService openFDAService;
  private final DrugRecordApplicationMapper mapper;

  @Override
  public ResponseEntity<OpenFDAResponse> searchSubmittedForApproval(
      String manufacturer,
      String brandName,
      @RequestParam(defaultValue = OpenFDAAPI.DEFAULT_PAGE) Integer page,
      @RequestParam(defaultValue = OpenFDAAPI.DEFAULT_COUNT) Integer pageSize) {
    return new ResponseEntity(
        openFDAService.findSubmittedForApproval(manufacturer, brandName, page, pageSize),
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<DrugRecordApplicationResource>> searchStoredInSystem(
      @Valid @RequestBody Map<Object, Object> fields,
      @RequestParam(defaultValue = OpenFDAAPI.DEFAULT_PAGE) Integer page,
      @RequestParam(defaultValue = OpenFDAAPI.DEFAULT_COUNT) Integer pageSize)
      throws DrugRecordApplicationNotFoundException, IllegalAccessException {

    return new ResponseEntity(openFDAService.findByQuery(fields, page, pageSize), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<DrugRecordApplicationResource> create(
      @Valid DrugRecordApplicationCreateRequest request) {
    return new ResponseEntity(
        openFDAService.create(mapper.toDrugRecordApplication(request)), HttpStatus.CREATED);
  }
}
