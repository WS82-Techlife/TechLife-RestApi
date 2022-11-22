package com.appbient.restapi.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
@Entity
public class Project {
	@Id
	@Column(name="id_project")
	private Integer id;
	private String name;
	@Lob
	private String description;
    @JsonProperty("creation_date")
    @Column(name="creation_date")
    private LocalDateTime creationDate;
	@ManyToOne
	@JoinColumn(name="id_userOng")
	@JsonProperty("ong_id")
	private UserOng userOng;
	@Lob
	private String location;
	private double lat;
	private double lng;
	@Lob
	private String mission;
	@Lob
	private String functions;
	@Lob
	@JsonProperty("photo_urls")
	private String photoUrls;
	@Lob
	private String requirements;
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Application> applications;
	
	public Project(int id) {
		this.id=id;
	}
}
