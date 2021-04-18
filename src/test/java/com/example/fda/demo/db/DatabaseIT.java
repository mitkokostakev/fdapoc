package com.example.fda.demo.db;

import com.example.fda.demo.DemoApplication;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles({"test"})
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(value = {"classpath:application.yaml"})
public abstract class DatabaseIT {

  @ClassRule public static TestPostgresContainer postgres = TestPostgresContainer.getInstance();
}
