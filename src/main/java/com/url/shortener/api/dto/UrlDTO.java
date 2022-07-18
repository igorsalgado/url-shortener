package com.url.shortener.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UrlDTO {
    private String Url;
    private Date expirationDate;
}
