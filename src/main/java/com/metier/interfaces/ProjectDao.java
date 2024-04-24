package com.metier.interfaces;

import java.util.List;

import com.entity.Project;
import com.entity.Task;

import jakarta.ejb.Local;

@Local
public interface ProjectDao {
	List<Project> getAllProjects();
	void createProject(Project project);
	void updateProject(Project project);
	Project removeProject(String code);
	void addTaskToProject(Task t);
	void removeTaskFromProject(Task t);
	Project getProjectByCode(String codeProj);
}
