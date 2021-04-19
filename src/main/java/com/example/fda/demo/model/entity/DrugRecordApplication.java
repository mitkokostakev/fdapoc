package com.example.fda.demo.model.entity;

import javax.persistence.*;
import lombok.*;

/**
 * Entity class defining ORM to the corresponding table in database for persistence of
 * DrugRecordApplication entries.
 */
@Data
@Entity
@Table(
    schema = "fda_demo",
    name = "drug_record_application",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = {"application_number", "manufacturer_name", "substance_name"})
    })
@Builder
@NoArgsConstructor
@AllArgsConstructor(
    access = AccessLevel.PRIVATE) // Hides the constructor to force useage of the Builder.
public class DrugRecordApplication {

  @Id
  @Column(name = "application_number", nullable = false)
  private String applicationNumber; // (as id)

  @Column(name = "manufacturer_name", nullable = false)
  private String manufacturerName;

  @Column(name = "substance_name", nullable = false)
  private String substanceName;

  @Column(name = "product_numbers", nullable = false)
  private String productNumbers;
  /** This field contains value whether the record was softly deleted */
  @Column(name = "deleted", columnDefinition = "BOOLEAN NOT NULL DEFAULT false")
  private Boolean deleted;
}
