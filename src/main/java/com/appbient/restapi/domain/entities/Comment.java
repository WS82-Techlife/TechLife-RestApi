package com.appbient.restapi.domain.entities;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//<!--<version>3.0.0-RC1</version>--->
@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
@Entity
public class Comment {
    @Id
    @Column(name="id_comment")
    private Integer id;
    @Lob
    private String content;
    @Column(name="creation_date")
    private LocalDateTime creationDate;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userOng_id")
    private UserOng ongAuthor;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userVolunteer_id")
    private UserVolunteer volunteerAuthor;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_publication")
    @JsonIgnore
    private Publication publication;
}
