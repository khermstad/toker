package com.khermstad.toker.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class BasicTokenPrimaryKey implements Serializable {

    private static final long serialVersionUID = 2569214898639795338L;

    @Id
    @Column(name = "APPLICATION_ID")
    private String applicationId;

    @Id
    @Column(name = "VALUE")
    private String value;
}
