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
public class Publication {
    @Id
    @Column(name="id_publication")
    private Integer id;
    @Lob
    private String title;
    @Lob
    private String content;
    @JsonProperty("creation_date")
    @Column(name="creation_date")
    private LocalDateTime creationDate;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_userOng")
    @JsonIgnore
    private UserOng ongAuthor;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_userVolunteer")
    @JsonIgnore
    private UserVolunteer volunteerAuthor;
    @OneToMany(mappedBy = "publication",fetch = FetchType.LAZY)
    private List<Comment> comments; 
    
    public Publication(Integer id) {
    	this.id=id;
    }
}
