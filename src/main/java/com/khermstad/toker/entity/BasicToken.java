package com.khermstad.toker.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "basic_token")
public class BasicToken {
    @Id
    @GeneratedValue
    @Column(name="ID")
    private Long id;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
}
