package com.appbient.restapi.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
@Entity
public class Application {
    @Id
    @Column(name="id_application")
	private Integer id;
    private Integer status; //0:pending, 1:accepted, 2: rejected
    @JsonProperty("creation_date")
    @Column(name="creation_date")
    private LocalDateTime creationDate;
	@ManyToOne
	@JoinColumn(name="id_project")
	private Project project;
	@ManyToOne
	@JoinColumn(name="id_userVolunteer")
	private UserVolunteer applicant;
	
	public Application(Integer id){
		this.id=id;
	}
}
