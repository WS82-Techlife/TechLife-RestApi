package com.appbient.restapi.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appbient.restapi.domain.entities.Application;
import com.appbient.restapi.domain.entities.Project;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{
    @Query(value="select * from application a inner join project p on a.id_project=p.id_project inner join userong u on p.id_userOng=u.id_userOng where u.id_userOng=?1",nativeQuery = true)
    List<Application> findApplicationsByUserOng(Integer id);
    
    @Query(value="select * from application a where a.id_userVolunteer=?1",nativeQuery = true)
    List<Application> findApplicationsByUserVolunteer(Integer id);
    
    @Query(value="select * from application a where a.id_userVolunteer=?1 and a.id_project=?2",nativeQuery = true)
    List<Application> findApplicationByUserToProject(Integer userId, Integer projectId);
}
