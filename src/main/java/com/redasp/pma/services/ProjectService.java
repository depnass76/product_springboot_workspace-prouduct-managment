package com.redasp.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redasp.pma.dao.IProjectRepository;
import com.redasp.pma.dto.ProjectStage;
import com.redasp.pma.dto.ProjectTimeLine;
import com.redasp.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	IProjectRepository proRepo;
	
	
	public Project save(Project project)
	{
		return proRepo.save(project);
	}
	
	public List<Project> getAll()
	{
		return proRepo.findAll();
	}
	
	public List<ProjectStage> getProjectStatus()
	{
		return proRepo.getProjectStatus();
	}

	

	public void delete(Project theProj) {
		proRepo.delete(theProj);
		
	}

	public Project findByProjectId(long theId) {
		
		return proRepo.findByProjectId(theId);
	}
	
	
	public List<ProjectTimeLine> getTimeData()
	{
		return proRepo.getDateTime();
	}
	 

	

}
