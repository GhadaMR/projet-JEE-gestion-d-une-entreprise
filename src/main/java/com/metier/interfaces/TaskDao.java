package com.metier.interfaces;

import java.util.List;

import com.entity.Task;

import jakarta.ejb.Local;

@Local
public interface TaskDao {
	List<Task> getAllTasks();
	void createTask(Task task);
	void updateTask(Task task);
	Task removeTask(String code);
}
