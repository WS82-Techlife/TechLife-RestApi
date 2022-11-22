package com.appbient.restapi.domain.services;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.appbient.restapi.application.controllers.dto.ApplicationCreationDTO;
import com.appbient.restapi.domain.commands.CreateApplicationCommand;
import com.appbient.restapi.domain.commands.DeleteApplicationCommand;
import com.appbient.restapi.domain.commands.EvaluateApplicationCommand;
import com.appbient.restapi.domain.entities.Application;
import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.entities.UserVolunteer;
import com.appbient.restapi.domain.exceptions.ResourceNotFoundException;
import com.appbient.restapi.domain.projectors.ApplicationProjection;
@Service
public class ApplicationCommandService {
	private final CommandGateway commandGateway;
	
    private final ApplicationProjection applicationProjection;
//	
	public ApplicationCommandService (CommandGateway commandGateway, ApplicationProjection applicationProjection) {
		this.commandGateway=commandGateway;
		this.applicationProjection=applicationProjection;
	}
	public CompletableFuture<ResponseEntity<String>> createApplication(ApplicationCreationDTO creationDTO){
		if(creationDTO.getProject_id()== null || creationDTO.getUser_volunteer_id()==null) {
			throw new ResourceNotFoundException("El campo de proyect_id o user_volunteer_id es null");
		}
		
		CreateApplicationCommand command=new CreateApplicationCommand(
				ServiceUtils.generateUniqueId(),
				0,
				creationDTO.getCreation_date(),
				new Project(creationDTO.getProject_id()),
				new UserVolunteer(creationDTO.getUser_volunteer_id())
		);
		Application newApplication=null;
		try {
			newApplication =this.applicationProjection.createApplication(command);
		}catch(Exception e) {
			throw e;
		}
		
		String message="{\"message\":\"Aplicacino creada con exito\", \"id_application\":\""+newApplication.getId().toString()+"\"}";

		return this.commandGateway.send(command).thenApply(it->ResponseEntity.ok(message))
				.exceptionally(e -> {
                    return ResponseEntity.badRequest().body(e.getMessage());
                });
	}
	public CompletableFuture<ResponseEntity<String>> deleteApplication(Integer id){
		DeleteApplicationCommand command=new DeleteApplicationCommand(id);
		try {
			this.applicationProjection.deleteApplication(command);
		}catch(Exception e) {
			throw e;
		}
		
		return this.commandGateway.send(command).thenApply(it->ResponseEntity.ok("Aplicacion eliminada con exito"))
				.exceptionally(e -> {
                    return ResponseEntity.badRequest().body(e.getMessage());
                });
	}
	public CompletableFuture<ResponseEntity<String>> evaluateApplication(Integer id, Integer status){
		if(status!= 1 && status!=2) {
			throw new ResourceNotFoundException("Valor de estado de la aplicacion no contemplado, debe ser 1 o 2");
		}
		EvaluateApplicationCommand command=new EvaluateApplicationCommand(id,status); 
		try {
			this.applicationProjection.updateStatus(command);
		}catch(Exception e) {
			throw e;
		}
		
		return this.commandGateway.send(command).thenApply(it->ResponseEntity.ok("Aplicacion evaluada con exito"))
				.exceptionally(e -> {
                    return ResponseEntity.badRequest().body(e.getMessage());
                });
	}
	
	
}
