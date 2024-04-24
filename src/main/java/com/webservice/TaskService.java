package com.webservice;

import java.util.List;

import com.entity.Project;
import com.entity.Task;
import com.metier.interfaces.ProjectDao;
import com.metier.interfaces.TaskDao;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Stateless
@Path("/task")
public class TaskService {

	@EJB
	TaskDao taskDao;
	
	@EJB
	ProjectDao projectDao; 

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTasks() {
	    try {
	        List<Task> tasks = taskDao.getAllTasks();
	        return Response.status(Response.Status.OK).entity(tasks).build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving tasks").build();
	    }
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTask(Task task) {
	    try {
	        // Fetch the project from the database based on the provided code
	        Project project = projectDao.getProjectByCode(task.getProject().getCode());

	        // Create the task with the existing project
	        Task t = new Task(task.getCode(), task.getDescription(), task.getStartDate(), task.getEndDate(), project);
	        taskDao.createTask(t);

	        return Response.status(Response.Status.CREATED).entity(t).build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating task").build();
	    }
	}



	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTask(Task task) {
	    try {
	    	Project project = projectDao.getProjectByCode(task.getProject().getCode());

	        Task t = new Task(task.getCode(), task.getDescription(), task.getStartDate(), task.getEndDate(), project);
	        taskDao.updateTask(t);
	        return Response.status(Response.Status.OK).entity(t).build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating task").build();
	    }
	}

	@DELETE
	@Path("/remove/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeTask(@PathParam(value="code") String code) {
	    try {
	        Task removedTask = taskDao.removeTask(code);
	        if (removedTask != null) {
	            return Response.status(Response.Status.OK).entity(removedTask).build();
	        } else {
	            return Response.status(Response.Status.NOT_FOUND).entity("Task not found").build();
	        }
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error removing task").build();
	    }
	}

}
