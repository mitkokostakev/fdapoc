package com.example.fda.demo.integration.fda.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Submission {
  @JsonProperty("submission_type")
  private String submissionType;

  @JsonProperty("submission_number")
  private String submissionNumber;

  @JsonProperty("submission_status")
  private String submissionStatus;

  @JsonProperty("submission_status_date")
  private String submissionStatusDate;

  @JsonProperty("review_priority")
  private String reviewPriority;

  @JsonProperty("submission_class_code")
  private String submissionClassCode;

  @JsonProperty("submission_class_code_description")
  private String submissionClassCodeDescription;

  @JsonProperty("application_docs")
  private List<ApplicationDoc> applicationDocs;
}
