package com.example.fda.demo.integration.fda.client;

import com.example.fda.demo.integration.fda.model.OpenFDAResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "openfdaclient", url = "${services.openfda.api.url}")
public interface OpenFDAClient {

  @RequestMapping(
      path = "/drug/drugsfda.json",
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      method = RequestMethod.GET)
  ResponseEntity<OpenFDAResponse> findByManufacturerAndBrand(
      @RequestParam(name = "search") String search);
}
