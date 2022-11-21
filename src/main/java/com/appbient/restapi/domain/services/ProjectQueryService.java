package com.appbient.restapi.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.entities.Publication;
import com.appbient.restapi.domain.exceptions.ResourceNotFoundException;
import com.appbient.restapi.domain.repositories.ProjectRepository;

@Service
public class ProjectQueryService {
	@Autowired
	ProjectRepository projectRepository;
	public List<Project> findAll(){
		return this.projectRepository.findAll();
	}
	public Project findById(Integer userId){
		Project project=this.projectRepository.findById(userId).orElse(null);
		if(project!=null) {
			return project;
		}else {
			throw new ResourceNotFoundException("No se encontro un proyecto con ese ID");
		}
	}
	public List<Project> findByUser(Integer userId){
		return this.projectRepository.findProjectsByUser(userId);
	}
	public List<Project> findByKeyword(String keyword){
		return this.projectRepository.findProjectsByKeyword(keyword);
	}
	public List<Project> findNearbyProjects(double lat, double lng){
		return this.projectRepository.findNearByProjects(lat,lng);
	}
}