package com.example.fda.demo.model.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.example.fda.demo.dummy.DummyDataProducer;
import com.example.fda.demo.model.dto.DrugRecordApplicationCreateRequest;
import com.example.fda.demo.model.entity.DrugRecordApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class DrugRecordApplicationMapperTest {

  private DrugRecordApplicationMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new DrugRecordApplicationMapperImpl();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void testToDrugRecordApplication() {
    // Given
    DrugRecordApplication drugRecordApplication =
        DummyDataProducer.generateDefaultDrugRecordApplication();
    drugRecordApplication.setDeleted(null);
    DrugRecordApplicationCreateRequest drugRecordApplicationCreateRequest =
        DummyDataProducer.generateDefaultDrugRecordApplicationCreateRequest();

    // When

    // Then
    DrugRecordApplication drugRecordApplicationMapped =
        mapper.toDrugRecordApplication(drugRecordApplicationCreateRequest);

    // Asserts
    assertNotNull(drugRecordApplicationMapped);
    assertEquals(drugRecordApplication, drugRecordApplicationMapped);
  }

  @Test
  void toDrugRecordApplicationResponse() {
      fail();
  }

  @Test
  void updateObjectValues() {
      fail();
  }

  @Test
  void arrayToString() {
      fail();
  }

  @Test
  void stringToArray() {
      fail();
  }
}
