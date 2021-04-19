package com.example.fda.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FDAApplication {

  public static void main(String[] args) {
    SpringApplication.run(FDAApplication.class, args);
  }
}
