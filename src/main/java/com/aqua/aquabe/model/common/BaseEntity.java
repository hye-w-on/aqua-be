package com.aqua.aquabe.model.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    // private String option1;
    // private String option2;
    // private String option3;
    // private String option4;
    // private String option5;

    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_datetime", insertable = false, updatable = false)
    private LocalDateTime createdDatetime;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "updated_datetime", insertable = false, updatable = false)
    private LocalDateTime updatedDatetime;

}