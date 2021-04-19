package com.example.fda.demo;

import com.example.fda.demo.controller.FDAController;
import com.example.fda.demo.db.DatabaseIT;
import com.example.fda.demo.integration.fda.model.OpenFDAResponse;
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

  @Test
  public void contextLoads() {
    Assertions.assertThat(fdaController).isNotNull();
  }

  @Test
  public void testSearchInRemoteAPI() {
    // Given
    String manufacturer = "ROCHE";
    String brandName = "SOLATENE";
    Integer page = 1;
    Integer pageSize = 10;

    // When
    ResponseEntity<OpenFDAResponse> openFDAResponseResponseEntity =
        fdaController.searchSubmittedForApproval(manufacturer, brandName, page, pageSize);

    // Then
    Assert.assertNotNull(openFDAResponseResponseEntity);
  }
}
