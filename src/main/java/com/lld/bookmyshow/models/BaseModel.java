package com.lld.bookmyshow.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass // Don't create a sep table, instead put its attributes to every child
public class BaseModel {
    @Id
    private Long id;

    private Date createdDate;

    private Date lastModifiedAt;
}
