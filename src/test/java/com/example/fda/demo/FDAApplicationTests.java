package com.example.fda.demo;

import com.example.fda.demo.controller.FDAController;
import com.example.fda.demo.db.DatabaseIT;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
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
}
