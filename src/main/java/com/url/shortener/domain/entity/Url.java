package com.url.shortener.domain.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "url")
@Data
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //indica que o id será gerado automaticamente
    private Long id;

    @Column(nullable = false) //indica que o campo não pode ser nulo
    private String longUrl;

    @Column(nullable = false)
    private Date createdDate;

    private Date expirationDate;
}

