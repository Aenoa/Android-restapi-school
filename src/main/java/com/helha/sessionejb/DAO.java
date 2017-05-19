package com.helha.sessionejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.helha.entities.APIStatus;
import com.helha.entities.Project;
import com.helha.entities.User;

@Stateless
@LocalBean
public class DAO {
	
	@PersistenceContext (unitName="pgroupe6")
	private EntityManager em;
	private static DAO instance = null;
	
	public DAO() {
		// TODO Auto-generated constructor stub
	}
	
	public String bonjour(){
		return "Bonjour de DAOProject"+System.lineSeparator();
	}
	
	public static DAO getInstance()
	{
		if(instance == null)
		{
			instance = new DAO();
		}
		return instance;
	}
	// ------ [ PROJECTS ] -------
	public List<Project> afficherProjets()
	{
		Query affProj = em.createQuery("SELECT p FROM Project p ORDER BY p.startDate_project DESC");
		return affProj.getResultList();
	}
	
	public Project afficherProjet(Integer id)
	{
		Project proj =  em.find(Project.class, id);
		return proj;
	}
	
	public List<Project> afficherProjetsParCategorie(String categorie)
	{
		Query affProj = em.createQuery("SELECT p FROM Project p WHERE p.category_project = :cat ORDER BY p.startDate_project DESC");
		affProj.setParameter("cat", categorie);
		List<Project> projs = (List<Project>) affProj.getResultList();
		return projs;
	}
	
	public List<Project> findProjectByName (String name){
		Query affproj = em.createQuery("SELECT p FROM Project p WHERE p.name_project LIKE :name");
		affproj.setParameter("name", "%" + name + "%");
		return affproj.getResultList();
	}
	
	public Project ajouterProject(Integer id_user, Project p)
	{
		em.persist(p);
		User user =  em.find(User.class, id_user);
		boolean status = user.ajoutProject(p);
		System.out.println("** ONLY-UP ** status DAO.ajouterProject (user.ajoutProject): "+status);
		return p;
	}
	
	public boolean supprimerProject(Integer id)
	{
		Project p = em.find(Project.class, id);
		List<User> listeUsers = this.afficherUsers();
		if(p == null)
		{
			System.out.println("** ONLY-UP ** Project object is null, cancelling.");
			return false;
		}
		else
		{
			System.out.println("** ONLY-UP ** Project found is " + p.toString());
			for(User u : listeUsers)
			{
				System.out.println("** ONLY-UP ** looping to user " + u.toString());
				if(u.ownsProject(p))
				{
					System.out.println("** ONLY-UP ** User #"+u.getId_user() +" owns project ID " + p.getId_project());
					boolean uR = u.removeProject(p);
					em.remove(p);
					return uR;
				}
			}	
		}
		return false;
	}
	
	public boolean modifierProject(int id, Project p)
	{
		try
		{
			Project data = em.find(Project.class, id);
			data.setName_project(p.getName_project());
			data.setPicture_project(p.getPicture_project());
			data.setDescription_project(p.getDescription_project());
			data.setDuration_project(p.getDuration_project());
			data.setExpectedPrice_project(p.getExpectedPrice_project());
			data.setStartDate_project(p.getStartDate_project());
			data.setCurrentAmount_project(p.getCurrentAmount_project());
			data.setContributors_projects(p.getContributors_projects());
			data.setCategory_project(p.getCategory_project());
		}
		catch(RollbackException e)
		{
			 return false;
		}
		return true;
	}
	
	public boolean addContributionProjet(Integer uid, Integer pid, double amount)
	{
		Project p = em.find(Project.class, pid);
		User u = em.find(User.class,  uid);
		
		if(u == null || p == null)
			return false;
		System.out.println("**TEST** " + u.getEmail_user());
		p.addContributor(u.getEmail_user());
		p.setCurrentAmount_project(p.getCurrentAmount_project() + amount);
		return true;
	}
	
	/*USER*/
	
	//Méthode permettant d'afficher tous les utilisateurs
	public List<User> afficherUsers()
	{
		Query affUser = em.createQuery("SELECT u FROM User u");
		return affUser.getResultList();
	}
	
	//Méthode permettant d'afficher un user en passant un identifiant
	public User afficherUser(int id)
	{
		User proj =  em.find(User.class, id);
		return proj;
	}
	
	//Méthode permettant d'afficher un utilisateur selon son adresse mail
	public List<User> afficherMail(String mail)
	{
		Query q = em.createQuery("SELECT u FROM User u WHERE u.email_user = :mail");
		q.setParameter("mail", mail);
		return q.getResultList();
	}
	
	//Methode permettant d'ajouter un utilisateur
	public User ajouterUser(User u)
	{
		em.persist(u);
		return u;
	}
	
	//methode permettant de supprimer un utilisateur 
	public boolean supprimerUser(Integer id)
	{
		User p = em.find(User.class, id);
		if(p == null)
		{
			return false;
		}
		else
		{
			em.remove(p);return true;
		}
	}
	/**
	Permet de modifier l'utilisateur correspondant à l' ID, en y injectant les valeurs de l'utilisateur passé en paramètre.
	@param id L'identifiant de la personne à modifier en base de donnée
	@param p La personne à éditer en base de donnée
	@return APIStatus l'état de la requête. vaut "OK" si tout se passe bien, "FAILED" en cas d'échec. Des informations supplémentaires sont trouvables en appelant la méthode getDescription() de APIStatus
	 */
	public boolean modifierUser(int id, User u)
	{
		//APIStatus s;
		try
		{			
			User data = em.find(User.class, id);
			data.setEmail_user(u.getEmail_user());
			data.setLevel_user(u.getLevel_user());
			data.setName_user(u.getName_user());
			data.setPassword_user(u.getPassword_user());
			data.setProjectlist(u.getProjectlist());
			data.setSurname_user(u.getSurname_user());
			
			//s = new APIStatus("OK", "/");
		}
		catch(RollbackException e)
		{
			 //s = new APIStatus("FAILED", e.getMessage());
			return false;	
		}
		return true;
	}	
	
}
