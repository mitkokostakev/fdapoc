package com.example.fda.demo.integration.fda.client;

import lombok.Data;

@Data
public class Params {
  private String search;
  private Integer limit;
  private Integer skip;
}
