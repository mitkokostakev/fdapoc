package com.example.fda.demo.model.dto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(
    value = "DrugRecordApplicationCreate",
    description = "Drug Record Application create model")
public class DrugRecordApplicationCreateRequest {

  @NotNull
  @ApiModelProperty(required = true)
  private String applicationNumber; // (as id)

  @NotNull
  @ApiModelProperty(required = true)
  private String manufacturerName;

  @NotNull
  @ApiModelProperty(required = true)
  private String substanceName;

  @NotNull
  @ApiModelProperty(required = true)
  private List<String> productNumbers;
}
