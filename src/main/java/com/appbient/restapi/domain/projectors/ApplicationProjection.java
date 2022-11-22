package com.appbient.restapi.domain.projectors;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.appbient.restapi.domain.commands.CreateApplicationCommand;
import com.appbient.restapi.domain.commands.DeleteApplicationCommand;
import com.appbient.restapi.domain.commands.EvaluateApplicationCommand;
import com.appbient.restapi.domain.entities.Application;
import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.entities.UserVolunteer;
import com.appbient.restapi.domain.exceptions.ResourceNotFoundException;
import com.appbient.restapi.domain.repositories.ApplicationRepository;
import com.appbient.restapi.domain.repositories.ProjectRepository;
import com.appbient.restapi.domain.repositories.UserVolunteerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApplicationProjection {
	private final ApplicationRepository applicationRepository;
	private final ProjectRepository projectRepository;
	private final UserVolunteerRepository userVolunteerRepository;
	
	public Application createApplication(CreateApplicationCommand command) {
		int flagForeignsAreValid=0;
		if(command.getApplicant().getId()!=null) {
			Optional<UserVolunteer> optionalApplicant= this.userVolunteerRepository.findById(command.getApplicant().getId());
			if(!optionalApplicant.isPresent() ) {
				throw new ResourceNotFoundException("El usuario ingresado no existe en la base de datos");
			}
			flagForeignsAreValid++;
		}
		if(command.getProject().getId()!=null){
			Optional<Project> optionalProject=this.projectRepository.findById(command.getProject().getId());
			if(!optionalProject.isPresent()) {
				throw new ResourceNotFoundException("El proyecto ingresado no existe en la base de datos");
			}
			flagForeignsAreValid++;
		}
		if(flagForeignsAreValid<2){
			throw new ResourceNotFoundException("Debe ingresar un proyecto y un usuario voluntairo para poder crear una aplicacion");
		}
		
		Application application=new Application(
			command.getId(),
			command.getStatus(),
			command.getCreationDate(),
			command.getProject(),
			command.getApplicant()
		);
		return applicationRepository.save(application);
		
	}

	public void deleteApplication(DeleteApplicationCommand command) {
		Optional<Application> optionalApplication= this.applicationRepository.findById(command.getId());
		if(optionalApplication.isPresent()) {
			Application application=optionalApplication.get();
			this.applicationRepository.delete(application);
		}else {
			throw new ResourceNotFoundException("La aplicacion con id " + command.getId() + " no Existe en la bd");
		}
	}
	public void updateStatus(EvaluateApplicationCommand command) {
		if(command.getId()==null) {
			throw new ResourceNotFoundException("El id de la aplicacion no puede ser null");
		}
		Optional<Application> optionalApplication= this.applicationRepository.findById(command.getId());
		if(optionalApplication.isPresent()) {
			Application application=optionalApplication.get();
			application.setStatus(command.getStatus());
			this.applicationRepository.save(application);
		}else {
			throw new ResourceNotFoundException("La aplicacion con id " + command.getId() + " no Existe en la bd");
		}
	}
}