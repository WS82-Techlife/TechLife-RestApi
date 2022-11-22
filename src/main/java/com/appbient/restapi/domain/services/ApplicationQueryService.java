package com.appbient.restapi.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appbient.restapi.domain.entities.Application;
import com.appbient.restapi.domain.entities.Project;
import com.appbient.restapi.domain.exceptions.ResourceNotFoundException;
import com.appbient.restapi.domain.repositories.ApplicationRepository;

@Service
public class ApplicationQueryService {
	@Autowired
	ApplicationRepository applicationRepository;
	
	public List<Application> findAllByUserOng(Integer userOngId){
		return this.applicationRepository.findApplicationsByUserOng(userOngId);
	}
	public List<Application> findAllByUserVolunteer(Integer userVolunteerId){
		return this.applicationRepository.findApplicationsByUserVolunteer(userVolunteerId);
	}
	
}