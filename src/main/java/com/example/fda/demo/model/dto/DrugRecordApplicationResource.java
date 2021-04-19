package com.example.fda.demo.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class DrugRecordApplicationResource {

  private String applicationNumber; // (as id)
  private String manufacturerName;
  private String substanceName;
  private List<String> productNumbers;
}
