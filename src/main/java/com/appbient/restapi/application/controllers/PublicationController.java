package com.appbient.restapi.application.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appbient.restapi.application.controllers.dto.CommentCreationDTO;
import com.appbient.restapi.application.controllers.dto.PublicationCreationDTO;
import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.services.PublicationCommandService;
import com.appbient.restapi.domain.services.PublicationQueryService;

@RestController
@RequestMapping(value = "/foro")
//@Api(value = "Bank Account Commands", description = "Bank Account Commands API")
public class PublicationController {
	@Autowired
	private PublicationCommandService publicationCommandService;
	@Autowired
	private PublicationQueryService publicationQueryService;
	@CrossOrigin
	@PostMapping(value="/publications")
	public CompletableFuture<ResponseEntity<String>> createPublication(@RequestBody PublicationCreationDTO creationDTO){
		return this.publicationCommandService.createPublication(creationDTO);
	}
	@CrossOrigin
	@DeleteMapping(value="/publications/{id}")
	public CompletableFuture<ResponseEntity<String>> deletePublication(@PathVariable("id") int id){
		return this.publicationCommandService.deletePublication(id);
	}
	@CrossOrigin
	@GetMapping(value="/publications/{id}")
	public Publication getPublication(@PathVariable("id") int id){
		return this.publicationQueryService.findById(id);
	}
	@CrossOrigin
	@GetMapping(value="/publications")
	public List<Publication> getPublications(){
		return this.publicationQueryService.findAll();
	}
	@CrossOrigin
	@GetMapping(value="/publications/{type}/{id}")
	public List<Publication> getPublicationByUser(@PathVariable("type")String userType,@PathVariable("id")int userId){
		return this.publicationQueryService.findAllByUser(userType,userId);
	}
	@CrossOrigin
	@PostMapping(value="/publications/{id}/comments")
	public CompletableFuture<ResponseEntity<String>> createComments(@RequestBody CommentCreationDTO creationDTO, @PathVariable("id") Integer publicationId){
		return this.publicationCommandService.createComment(creationDTO, publicationId);
	}
	@CrossOrigin
	@DeleteMapping(value="/publications/comments/{id}")
	public CompletableFuture<ResponseEntity<String>> deleteComment(@PathVariable("id") int id){
		return this.publicationCommandService.deleteComment(id);
	}

}

//"title":"Consejos para reducir el uso de plasticos",
//"content":"contenido de la publicacion",
//"ong_id":null,
//"volunteer_id":4