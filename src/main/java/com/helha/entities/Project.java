package com.helha.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_project;             // Identifiant du projet 
	private String picture_project;         // Référence Image
	private String name_project;            // Nom du projet
	private String category_project;        // Catégorie du projet 
	private double expectedPrice_project;   // Montant souhaité 
	private String description_project;     // Description du projet
	private int    duration_project;        // Temps en jour 
	private double currentAmount_project;   // Montant récolter
	private String startDate_project;       // Date de début du projet 
	
	private List<String> contributors_projects;
	
	public Project() {
		this.contributors_projects = new ArrayList<>();
	}
	
	public Project(String name_projet, String category_project, double expectedPrice_project, String description_projet,
			int duration_project, String startDate_project) {
		super();
		this.name_project = name_projet;
		this.category_project = category_project;
		this.expectedPrice_project = expectedPrice_project;
		this.description_project = description_projet;
		this.duration_project = duration_project;
		this.startDate_project = startDate_project;
		this.contributors_projects = new ArrayList<>();
	}
	
	public Project(String name_projet, String category_project, double expectedPrice_project, String description_projet,
			int duration_project, double currentAmount_project, String startDate_project) {
		super();
		this.name_project = name_projet;
		this.category_project = category_project;
		this.expectedPrice_project = expectedPrice_project;
		this.description_project = description_projet;
		this.duration_project = duration_project;
		this.currentAmount_project = currentAmount_project;
		this.startDate_project = startDate_project;
		this.contributors_projects = new ArrayList<>();
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

	public void setName_project(String name_projet) {
		this.name_project = name_projet;
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

	public void setDescription_project(String description_projet) {
		this.description_project = description_projet;
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

	public List<String> getContributors_projects() {
		return contributors_projects;
	}

	public void setContributors_projects(List<String> contributors_projects) {
		this.contributors_projects = contributors_projects;
	}

	public Integer getId_project() {
		return id_project;
	}
	
	public void setId_project(Integer id_project) {
		this.id_project = id_project;
	}
	
	/*public void addcontributors(User u){
		if(!contributors_projects.contains(u))
			contributors_projects.add(u);
			
	}*/

	@Override
	public String toString() {
		return "Project [id_project=" + id_project + ", name_projet=" + name_project + ", category_project="
				+ category_project + ", expectedPrice_project=" + expectedPrice_project + ", description_projet="
				+ description_project + ", duration_project=" + duration_project + ", currentAmount_project="
				+ currentAmount_project + ", startDate_project=" + startDate_project + ", contributors_projects="
				+ contributors_projects + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_project == null) ? 0 : id_project.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (id_project == null) {
			if (other.id_project != null)
				return false;
		} else if (!id_project.equals(other.id_project))
			return false;
		return true;
	}
	
	public boolean addContributor(String u)
	{
		if(this.contributors_projects == null)
			this.contributors_projects = new ArrayList<>();
		return this.contributors_projects.add(u);
	}
}
