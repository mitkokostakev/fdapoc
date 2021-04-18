package com.example.fda.demo.integration.fda.model;

import java.util.List;
import lombok.Data;

@Data
public class OpenFDAResponse {
  private Meta meta;
  private List<Result> results;
}
