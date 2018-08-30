package com.khermstad.toker.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Table
@IdClass(BasicTokenPrimaryKey.class)
public class BasicToken implements Serializable {

    private static final long serialVersionUID = -6483400588886455520L;

    @Id
    @Column(name = "APPLICATION_ID")
    private String applicationId;

    @Id
    @Column(name = "VALUE")
    private String value;

    @Column(name = "IS_ENABLED")
    private boolean isEnabled;

    @Column(name = "CREATED")
    private Timestamp created;

    public BasicToken(String applicationId, String value, boolean isEnabled, Timestamp created){
        this.applicationId = applicationId;
        this.value = value;
        this.isEnabled = isEnabled;
        this.created = created;
    }
}
