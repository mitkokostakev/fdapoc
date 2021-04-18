package com.example.fda.demo.integration.fda.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Result {

  @JsonProperty("application_number")
  private String applicationNumber;

  @JsonProperty("sponsor_name")
  private String sponsorName;

  private List<Submission> submissions;
  private List<Product> products;
}
