package com.example.fda.demo;

import com.example.fda.demo.controller.FDAController;
import com.example.fda.demo.db.DatabaseIT;
import com.example.fda.demo.integration.fda.model.OpenFDAResponse;
import com.example.fda.demo.model.mapper.DrugRecordApplicationMapper;
import com.example.fda.demo.util.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = FDAApplication.class)
@ActiveProfiles("test")
@Profile("test")
@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FDAApplicationTests extends DatabaseIT {

  @Autowired private FDAController fdaController;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private DrugRecordApplicationMapper mapper;

  @Test
  public void contextLoads() {
    Assertions.assertThat(fdaController).isNotNull();
  }

  @Test
  public void testSearchInRemoteAPI() throws IOException {
    // Given
    String manufacturer = "ROCHE";
    String brandName = "SOLATENE";
    Integer page = 1;
    Integer pageSize = 10;

    // Get file from resources folder
    OpenFDAResponse openFDAResponse =
        readOpenFDAResponseFromPayload(
            "payloads/search/SearchResponseForManufactureRocheAndBrandNameSolatene.json");

    // When
    ResponseEntity<OpenFDAResponse> openFDAResponseResponseEntity =
        fdaController.searchSubmittedForApproval(manufacturer, brandName, page, pageSize);

    OpenFDAResponse responseEntityBody = openFDAResponseResponseEntity.getBody();

    // Then
    Assert.assertNotNull(openFDAResponseResponseEntity);
    Assert.assertEquals(responseEntityBody, openFDAResponse);
  }

  private OpenFDAResponse readOpenFDAResponseFromPayload(String payloadPath) throws IOException {
    String openFDAResponseJsonString = FileUtils.loadFileContents(payloadPath);
    OpenFDAResponse openFDAResponse =
        objectMapper.readValue(openFDAResponseJsonString, OpenFDAResponse.class);
    return openFDAResponse;
  }
}
