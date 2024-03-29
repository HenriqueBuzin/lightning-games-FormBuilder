package lightning.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lightning.model.User;
import lightning.service.UserService;

@Path("/user")
public class UserRest {

	@Inject
	private UserService service;
	
	@GET
	@Path("resetPassword/{id}")
	public void resetPassword(@PathParam("id") Integer id) {
		service.resetPassword(id);
	}
	
	@GET
	@Path("changePassword/{id}/{newPassword}")
	public void changePassword(
			@PathParam("id") Integer id, 
			@PathParam("newPassword") String newPassword) {
		service.changePassword(id, newPassword);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void create(User entity) {
		if (entity.getImage() == null || "".equals(entity.getImage())) {
			entity.setImage("no-image.png");
		}
		
		entity.setPassword(service.converterStringParaMD5(entity.getPassword()));
		service.create(entity);
	}
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(User entity) {
		return service.findByEmailAndPassword(entity);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void edit(User entity) {
		if (entity.getImage() == null || "".equals(entity.getImage())) {
			entity.setImage("no-image.png");
		}
		
		entity.setPassword(service.converterStringParaMD5(entity.getPassword()));
		service.edit(entity);
	}

	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") Integer id) {
		service.remove(service.find(id));
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User find(@PathParam("id") Integer id) {
		return service.find(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> findAll() {
		return service.findAll();
	}

}
