package com.appbient.restapi.domain.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appbient.restapi.application.controllers.dto.ProjectRetrivalDTO;
import com.appbient.restapi.domain.entities.Application;
import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.exceptions.ResourceNotFoundException;
import com.appbient.restapi.domain.repositories.ApplicationRepository;
import com.appbient.restapi.domain.repositories.ProjectRepository;

@Service
public class ProjectQueryService {
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	ApplicationRepository applicationRepository;
	public List<Project> findAll(){
		return this.projectRepository.findAll();
	}
	public ProjectRetrivalDTO findById(Integer projectId, Integer userId){
		Project project=this.projectRepository.findById(projectId).orElse(null);
		if(project==null) {
			throw new ResourceNotFoundException("No se encontro un proyecto con ese ID");
		}
		
		ModelMapper mapper=new ModelMapper();
		ProjectRetrivalDTO tmpDto=mapper.map(project, ProjectRetrivalDTO.class);
		tmpDto.setCantApplications(project.getApplications().size());
		
		if(userId!=null) {
			List<Application> applications=this.applicationRepository.findApplicationByUserToProject(userId, projectId);
			if(applications.size()>0) {
				tmpDto.setVolunteerApplicationId(applications.get(0).getId());	
			}
			
		}

		return tmpDto;
	}
	public List<Project> findByUser(Integer userId){
		return this.projectRepository.findProjectsByUser(userId);
	}
	public List<Project> findByKeyword(String keyword){
		return this.projectRepository.findProjectsByKeyword(keyword);
	}
	public List<ProjectRetrivalDTO> findNearbyProjects(double lat, double lng){
		List<Project> projects=this.projectRepository.findNearByProjects(lat,lng);
		List<ProjectRetrivalDTO> dtos=new ArrayList<ProjectRetrivalDTO>();
		ProjectRetrivalDTO tmpDto;
		ModelMapper mapper=new ModelMapper();
		for(int i=0;i<projects.size();i++) {
			tmpDto=mapper.map(projects.get(i), ProjectRetrivalDTO.class);
			tmpDto.setCantApplications(projects.get(i).getApplications().size());
			dtos.add(tmpDto);
		}
		return dtos;
	}
}