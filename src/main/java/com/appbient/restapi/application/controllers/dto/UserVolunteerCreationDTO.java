package com.appbient.restapi.application.controllers.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class UserVolunteerCreationDTO {
    private String name;
    private String email;
    private String password;
    @JsonProperty("firt_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private Date birthDate;
    private Integer genre;
    private Integer experience;
    private String dni;
}
