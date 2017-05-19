package com.helha.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.helha.entities.Project;
import com.helha.entities.User;
import com.helha.sessionejb.DAO;

@Named
@RequestScoped
public class Controller {
	private Integer id_user;
	private String name_user; 
	private String surname_user; 
	private int    level_user;
	private String email_user;
	private String password_user;
	private User newuser = new User();
	private List<User> users;
	
	private Integer id_project;             
	private String picture_project;        
	private String name_project;            
	private String category_project;      
	private double expectedPrice_project;   
	private String description_project;     
	private int    duration_project;        
	private double currentAmount_project;   
	private String startDate_project;  
	private Project projectnew = new Project();
	private List<Project> projects;
	private double montant; 
	
	@EJB
	private DAO dao;
	
	public List<User> doUserSelectAll(){
		return dao.afficherUsers();
	}
	
	public User doUserAdd(){
		dao.ajouterUser(newuser);
		newuser = new User();
		users = dao.afficherUsers();
		return newuser;
	}
	
	public String doUserDelete(User u) {
		dao.supprimerUser(u.getId_user());
		return doUserRefresh();
	}
	
	public void doUserFindUser () {
		List<User> users = dao.afficherMail(email_user);
		if(users == null || users.size() == 0){
			name_user = "";
			surname_user = "";
			level_user = 0;
			email_user = "";
			password_user = "";
		}
		else {
			name_user = users.get(0).getName_user();
			surname_user = users.get(0).getSurname_user();
			level_user = users.get(0).getLevel_user();
			email_user = users.get(0).getEmail_user();
			password_user = users.get(0).getPassword_user();
		}
	}
	
	public String doUserUpdate (User u){
		dao.modifierUser(u.getId_user(), u);
		return doUserRefresh();
	}
	
	
	public List<Project> doProjectSelectAll () {
		return dao.afficherProjets();
	}
	
	public String doProjectAdd(){
		List<User> users = dao.afficherMail(email_user);
		id_user = users.get(0).getId_user();
		dao.ajouterProject(id_user, projectnew);
		projectnew = new Project ();
		newuser = new User();
		email_user = "";
		projects = dao.afficherProjets();
		return doProjectRefresh();
	}
	
	public String doProjectDelete(Project p){
		dao.supprimerProject(p.getId_project());
		return doProjectRefresh();
	}
	
	public String doAddContributor(){
		List<User> users = dao.afficherMail(email_user);
		id_user = users.get(0).getId_user();
		dao.addContributionProjet(id_user, id_project, montant);
		return "selectAllProject.xhtml?faces-redirect=true";
	}
	
	public List<Project> doProjectSelectByCategory () {
		List<Project> proj = new ArrayList<>();
		System.out.println("COUCOU "+category_project);
		if(category_project == null)
			return proj = dao.afficherProjets();
		else if (category_project.equals("AfficherTout"))
			return dao.afficherProjets();
		else 
			return dao.afficherProjetsParCategorie(category_project);
	}
	
	public void doProjectFindProject(){
		Project proj = dao.afficherProjet(id_project);
		if(proj == null){
			name_project = "";            
			category_project = "";         
			expectedPrice_project = 0.0;    
			description_project = "";     
			duration_project = 0;         
			currentAmount_project = 0.0;   
			startDate_project= "";       
		}
		else {
			name_project = proj.getName_project();           
			category_project = proj.getCategory_project();         
			expectedPrice_project = proj.getExpectedPrice_project();    
			description_project = proj.getDescription_project();     
			duration_project = proj.getDuration_project();         
			currentAmount_project = proj.getCurrentAmount_project();   
			startDate_project= proj.getStartDate_project();    
		}
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public String getName_user() {
		return name_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	public String getSurname_user() {
		return surname_user;
	}

	public void setSurname_user(String surname_user) {
		this.surname_user = surname_user;
	}

	public int getLevel_user() {
		return level_user;
	}

	public void setLevel_user(int level_user) {
		this.level_user = level_user;
	}

	public String getEmail_user() {
		return email_user;
	}

	public void setEmail_user(String email_user) {
		this.email_user = email_user;
	}

	public String getPassword_user() {
		return password_user;
	}

	public void setPassword_user(String password_user) {
		this.password_user = password_user;
	}

	public User getNewuser() {
		return newuser;
	}

	public void setNewuser(User newuser) {
		this.newuser = newuser;
	}

	public List<User> getUsers() {
		if(users == null){
			users = dao.afficherUsers();
		}
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	

	public Integer getId_project() {
		return id_project;
	}

	public void setId_project(Integer id_project) {
		this.id_project = id_project;
	}

	public String getPicture_project() {
		return picture_project;
	}

	public void setPicture_project(String picture_project) {
		this.picture_project = picture_project;
	}

	public String getName_project() {
		return name_project;
	}

	public void setName_project(String name_project) {
		this.name_project = name_project;
	}

	public String getCategory_project() {
		return category_project;
	}

	public void setCategory_project(String category_project) {
		this.category_project = category_project;
	}

	public double getExpectedPrice_project() {
		return expectedPrice_project;
	}

	public void setExpectedPrice_project(double expectedPrice_project) {
		this.expectedPrice_project = expectedPrice_project;
	}

	public String getDescription_project() {
		return description_project;
	}

	public void setDescription_project(String description_project) {
		this.description_project = description_project;
	}

	public int getDuration_project() {
		return duration_project;
	}

	public void setDuration_project(int duration_project) {
		this.duration_project = duration_project;
	}

	public double getCurrentAmount_project() {
		return currentAmount_project;
	}

	public void setCurrentAmount_project(double currentAmount_project) {
		this.currentAmount_project = currentAmount_project;
	}

	public String getStartDate_project() {
		return startDate_project;
	}

	public void setStartDate_project(String startDate_project) {
		this.startDate_project = startDate_project;
	}

	public Project getProjectnew() {
		return projectnew;
	}

	public void setProjectnew(Project projectnew) {
		this.projectnew = projectnew;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String doUserRefresh () {
	    return "gestionUser.xhtml?faces-redirect=true";
	}
	
	public String doProjectRefresh () {
	    return "gestionProject.xhtml?faces-redirect=true";
	}
	
	
	
}
