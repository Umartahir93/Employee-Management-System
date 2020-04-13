package com.employee.management.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Base Model
 * 
 * @author umar.tahir
 *
 */
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public class BaseEntity {
  @CreatedBy
  @Column(name = "n_created_by")
  private String createdBy;

  @CreatedDate
  @Column(name = "n_created")
  private Timestamp created;

  @LastModifiedBy
  @Column(name = "n_last_modified_by")
  private String lastModifiedBy;

  @LastModifiedDate
  @Column(name = "n_last_modified")
  private Timestamp lastModified;

}
