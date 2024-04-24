package com.metier.impl;

import java.util.List;

import com.entity.Project;
import com.entity.Task;
import com.metier.interfaces.ProjectDao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Stateless
public class ProjectDaoImpl implements ProjectDao{

	@PersistenceContext(unitName = "projectManagementUnit")
	EntityManager em;

	@Override
	public List<Project> getAllProjects() {
		String requeteJPQL= "select p from Project p ";
		Query query= em.createQuery(requeteJPQL);

		return query.getResultList();
	}

	@Override
	public void createProject(Project project) {
		em.persist(project);
	}

	@Override
	public void updateProject(Project project) {
		em.merge(project);
	}

	@Override
	public Project removeProject(String code) {
		Project p = em.find(Project.class, code);
		em.remove(p);
		return p;
	}

	@Override
	public void addTaskToProject(Task t) {
		Project p = em.find(Project.class, t.getProject().getCode());
		p.getTasks().add(t);
		em.merge(p);
	}

	@Override
	public void removeTaskFromProject(Task t) {
	    Project p = em.find(Project.class, t.getProject().getCode());
	    p.getTasks().remove(t);
	    em.merge(p);
	}
	
	@Override
	public Project getProjectByCode(String codeProj) {
		 String requeteJPQL = "SELECT p FROM Project p WHERE p.code = :code";
		    Query query = em.createQuery(requeteJPQL);
		    query.setParameter("code", codeProj);

		    return (Project) query.getSingleResult();
	}

}
