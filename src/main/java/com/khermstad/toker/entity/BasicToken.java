package com.khermstad.toker.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "basic_token")
public class BasicToken {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name = "value")
    private String value;
}
