package com.example.fda.demo.integration.fda.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Product {
  @JsonProperty("product_number")
  private String productNumber;

  @JsonProperty("reference_drug")
  private String referenceDrug;

  @JsonProperty("brand_name")
  private String brandName;

  @JsonProperty("active_ingredients")
  private List<Ingredient> activeIngredients;

  @JsonProperty("reference_standard")
  private String referenceStandard;

  @JsonProperty("dosage_form")
  private String dosageForm;

  @JsonProperty("route")
  private String route;

  @JsonProperty("marketing_status")
  private String marketingStatus;
}
