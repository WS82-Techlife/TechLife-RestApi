package com.appbient.restapi.application.controllers.dto;

import java.time.LocalDateTime;

import javax.persistence.JoinColumn;

import com.appbient.restapi.domain.entities.UserOng;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class ProjectRetrivalDTO {
	private Integer id;
	private String name;
	private String description;
    @JsonProperty("creation_date")
    private LocalDateTime creationDate;
    @JsonProperty("ong_id")
	private UserOng userOng;
	private String location;
	private double lat;
	private double lng;
	private String mission;
	private String functions;
	@JsonProperty("photo_urls")
	private String photoUrls;
	private String requirements;
	private Integer cantApplications;
	@JsonProperty("volunteer_application_id")
	private Integer volunteerApplicationId;
}
