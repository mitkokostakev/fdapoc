package com.example.fda.demo.integration.fda.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Meta {
  private String disclaimer;
  private String terms;
  private String license;

  @JsonProperty("last_updated")
  private String lastUpdated;

  private MetaResult results;
}
