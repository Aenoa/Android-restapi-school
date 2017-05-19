package com.helha.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ws.rs.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.helha.entities.APIStatus;
import com.helha.entities.Project;
import com.helha.entities.User;
import com.helha.sessionejb.DAO;

@Path("/api")
public class RestAPI 
{
	@EJB
	private DAO dao = DAO.getInstance();
		
	@Path("/bonjour")
	@GET
	public String bonjour(){
		return "Bonjour";
	}
	
	@GET
	@Path("/project/selectAll")
	public List<Project> afficherProjets () {
		return dao.afficherProjets();
	}

	@GET
	@Path("/project/find/{id_project}")
	public Project afficherProjet (@PathParam("id_project") int id) {
		return dao.afficherProjet(id);
	}
	
	@GET
	@Path("/project/findByCategory/{category}")
	public List<Project> afficherProjetsParCategorie(@PathParam("category") String categorie){
		return dao.afficherProjetsParCategorie(categorie);
	}
	
	@GET
	@Path("/project/findByName/{name}")
	public List<Project> findProjectByName(@PathParam("name") String name){
		return dao.findProjectByName(name);
	}
	
	@POST
	@Path("/project/create/{id_user}/{name_project}/{category_project}/{expectedPrice_project}/{description_project}/{duration_project}/{startDate_project}")
	public Project ajouterProjet(
			@PathParam("id_user") Integer id,
			@PathParam("name_project") String name, 
			@PathParam("category_project") String category, 
			@PathParam("expectedPrice_project") double expectedPrice, 
			@PathParam("description_project") String description,
			@PathParam("duration_project") int duration,
			@PathParam("startDate_project") String date){
		Project proj = new Project(name, category, expectedPrice, description, duration, date);
		return dao.ajouterProject(id, proj);
	}
	
	@DELETE
	@Path("/project/delete/{id}")
	public boolean supprimerProject (@PathParam("id") Integer id){
		return dao.supprimerProject(id);
	}

	@DELETE
	@Path("/project/deleteByTitle/{title}")
	public boolean supprimerProjectByTitre (@PathParam("title") String title){
		List<Project> listeProjToDelete = dao.findProjectByName(title);
		for(Project p : listeProjToDelete)
		{
			System.out.println("** ONLY-UP ** Deleting project #"+p.getId_project());
			dao.supprimerProject(p.getId_project());
		}
		return true;
	}
	
	
	@PUT
	@Path("/project/update/{id}/{name_project}/{category_project}/{expectedPrice_project}/{description_project}/{duration_project}/{currentAmount_project}/{startDate_project}")
	public boolean modifierProjet (
			@PathParam("id") int id,
			@PathParam("picutre_project") String picture, 
			@PathParam("name_project") String name, 
			@PathParam("category_project") String category, 
			@PathParam("expectedPrice_project") double expectedPrice, 
			@PathParam("description_project") String description,
			@PathParam("duration_project") int duration,
			@PathParam("currentAmount_project") double currentAmount,
			@PathParam("startDate_project") String date){
		Project p = new Project(name, category, expectedPrice, description, duration, currentAmount, date);
		return dao.modifierProject(id, p);
	}
	
	@PUT
	@Path("/project/addContribution/{uid}/{pid}/{amount}")
	public boolean addContribProjet (
			@PathParam("uid") Integer uid,
			@PathParam("pid") Integer pid,
			@PathParam("amount") double v){
		//Project p = new Project(name, picture, category, expectedPrice, description, duration, currentAmount, date);
		return dao.addContributionProjet(uid, pid, v);
		//return dao.modifierProject(id, p);
	}
	
	@GET
	@Path("/user/selectAll")
	public List<User> afficherUsers(){
		return dao.afficherUsers();
	}
	
	@GET
	@Path("/user/find/{id}")
	public User afficherUser(@PathParam("id") int id){
		return dao.afficherUser(id);
	}
	
	@GET
	@Path("/user/findByEmail/{mail}")
	public List<User> afficherUsers(@PathParam("mail") String m)
	{
		return dao.afficherMail(m);
	}
	
	@POST
	@Path("/user/create/{name}/{surname}/{level}/{email}/{password}")
	public User ajouterUser(
			@PathParam("name") String u_name, 
			@PathParam("surname") String u_surname, 
			@PathParam("level") int u_level, 
			@PathParam("email") String u_email, 
			@PathParam("password") String u_password)
	{
		User u = new User(u_name, u_surname, u_level, u_email, u_password);
		return dao.ajouterUser(u);
	}
	
	@Path("/user/delete/{id}")
	@DELETE
	public boolean supprimerUser(@PathParam("id") Integer id)
	{
		return dao.supprimerUser(id);
	}

	@DELETE
	@Path("/user/deleteByEmail/{email}")
	public boolean supprimerUserByMail (@PathParam("email") String mail){
		List<User> listeUserToDelete = dao.afficherMail(mail);
		for(User p : listeUserToDelete)
		{
			dao.supprimerUser(p.getId_user());
		}
		return true;
	}
	
	@PUT
	@Path("/user/update/{id}/{name}/{surname}/{level}/{email}/{password}")
	public boolean modifierUser(
			@PathParam("id") int id,
			@PathParam("name") String u_name, 
			@PathParam("surname") String u_surname, 
			@PathParam("level") int u_level, 
			@PathParam("email") String u_email, 
			@PathParam("password") String u_password)
	{
		User u = new User(u_name, u_surname, u_level, u_email, u_password);
		return dao.modifierUser(id, u);
	}
	
}