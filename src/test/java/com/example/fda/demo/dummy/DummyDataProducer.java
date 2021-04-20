package com.example.fda.demo.dummy;

import com.example.fda.demo.model.dto.DrugRecordApplicationCreateRequest;
import com.example.fda.demo.model.entity.DrugRecordApplication;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DummyDataProducer {

  public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public static DrugRecordApplication generateDefaultDrugRecordApplication() {
    return DrugRecordApplication.builder()
        .applicationNumber("NDA017589")
        .manufacturerName("ROCHE")
        .substanceName("BETA CAROTENE")
        .productNumbers("product1,product2,product3")
        .deleted(false)
        .build();
  }

  public static DrugRecordApplicationCreateRequest
      generateDefaultDrugRecordApplicationCreateRequest() {
    return DrugRecordApplicationCreateRequest.builder()
        .applicationNumber("NDA017589")
        .manufacturerName("ROCHE")
        .substanceName("BETA CAROTENE")
        .productNumbers(Arrays.asList("product1", "product2", "product3"))
        .build();
  }
}
