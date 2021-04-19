package com.example.fda.demo.controller;

import com.example.fda.demo.exception.DrugRecordApplicationNotFoundException;
import com.example.fda.demo.integration.fda.model.OpenFDAResponse;
import com.example.fda.demo.model.dto.DrugRecordApplicationCreateRequest;
import com.example.fda.demo.model.dto.DrugRecordApplicationResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fda/applications")
@Api(value = "api/v1/fda/applications")
@Validated
public interface OpenFDAAPI {

  String DEFAULT_PAGE = "1";
  String DEFAULT_COUNT = "10";
  int MIN_COUNT_SIZE = 1;
  int MAX_COUNT_SIZE = 10;

  // GET

  // Swagger info section-------------------------------------------------
  @ApiOperation(
      value = "",
      notes = "Get all drug record applications submitted to FDA for approval",
      response = OpenFDAResponse.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successful response"),
        @ApiResponse(code = 404, message = "Not found response"),
        @ApiResponse(code = 422, message = "Missing or wrong arguments response")
      })
  // Swagger info section-------------------------------------------------
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<OpenFDAResponse> searchSubmittedForApproval(
      @RequestParam(value = "manufacturer") String manufacturer,
      @RequestParam(value = "brandName", required = false) String brandName,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "pageSize", required = false) @Min(MIN_COUNT_SIZE) @Max(MAX_COUNT_SIZE)
          Integer pageSize);

  // Swagger info section-------------------------------------------------
  @ApiOperation(
      value = "",
      notes = "Get all drug record applications stored in system",
      response = List.class)
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successful response"),
        @ApiResponse(code = 404, message = "Not found response"),
        @ApiResponse(code = 422, message = "Missing or wrong arguments response")
      })
  // Swagger info section-------------------------------------------------
  @RequestMapping(
      method = RequestMethod.GET,
      path = "/query",
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<DrugRecordApplicationResource>> searchStoredInSystem(
      @Valid @RequestBody Map<Object, Object> fields,
      @RequestParam(defaultValue = OpenFDAAPI.DEFAULT_PAGE) Integer page,
      @RequestParam(defaultValue = OpenFDAAPI.DEFAULT_COUNT) Integer pageSize)
      throws DrugRecordApplicationNotFoundException, IllegalAccessException;

  // POST

  // Swagger info section-------------------------------------------------
  @ApiOperation(
      value = "store specific drug record application details in my system",
      response = DrugRecordApplicationResource.class,
      tags = {})
  @ApiResponses(
      value = {
        @ApiResponse(
            code = 201,
            message = "Successfully created a drug record application",
            response = DrugRecordApplicationResource.class),
        @ApiResponse(code = 400, message = "No drug record application created due to bad request")
      })
  // Swagger info section-------------------------------------------------
  @RequestMapping(
      produces = {"application/hal+json", "application/problem+json", "application/json"},
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<DrugRecordApplicationResource> create(
      @RequestBody @Valid DrugRecordApplicationCreateRequest drugRecordApplicationCreateRequest);
}
