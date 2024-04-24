package com.metier.impl;

import java.util.List;

import com.entity.Task;
import com.metier.interfaces.ProjectDao;
import com.metier.interfaces.TaskDao;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class TaskDaoImpl implements TaskDao{

	@PersistenceContext(unitName = "projectManagementUnit")
	EntityManager em;

	@EJB
	ProjectDao projectDao;

	@Override
	public List<Task> getAllTasks() {
		String requeteJPQL= "select t from Task t ";
		Query query= em.createQuery(requeteJPQL);

		List<Task>  tasks = query.getResultList();

		return tasks;
	}

	@Override
	public void createTask(Task task) {
		em.persist(task);
	    projectDao.addTaskToProject(task);
	}

	@Override
	public void updateTask(Task task) {
		em.merge(task);
	}

	@Override
	public Task removeTask(String code) {
		Task t = em.find(Task.class, code);
		projectDao.removeTaskFromProject(t);
		em.remove(t);
		return t;
	}

}
