package com.webservice;

import java.util.List;

import com.entity.Project;
import com.metier.interfaces.ProjectDao;

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
@Path("/project")
public class ProjectService {

	@EJB
	ProjectDao projectDao;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProjects() {
	    try {
	        List<Project> projects = projectDao.getAllProjects();
	        return Response.status(Response.Status.OK).entity(projects).build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving projects").build();
	    }
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProject(Project project) {
	    try {
	        projectDao.createProject(project);
	        return Response.status(Response.Status.CREATED).entity(project).build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating project").build();
	    }
	}


	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProject(Project project){
	    try {
	        projectDao.updateProject(project);
	        return Response.status(Response.Status.OK).entity(project).build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating project").build();
	    }
	}

	@DELETE
	@Path("/remove/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeProject(@PathParam(value="code") String code) {
	    try {
	        Project removedProject = projectDao.removeProject(code);
	        if (removedProject != null) {
	            return Response.status(Response.Status.OK).entity(removedProject).build();
	        } else {
	            return Response.status(Response.Status.NOT_FOUND).entity("Project not found").build();
	        }
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error removing project").build();
	    }
	}

}
