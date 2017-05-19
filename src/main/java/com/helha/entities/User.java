package com.helha.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_user; 
	
	private String name_user; 
	private String surname_user; 
	private int    level_user;
	private String email_user;
	private String password_user;
	
	@OneToMany
	List<Project> projectlist;
	
	public User() {
		
	}
	
	public User(String name_user, String surname_user, int level_user, String email_user, String password_user) {
		super();
		this.name_user = name_user;
		this.surname_user = surname_user;
		this.level_user = level_user;
		this.email_user = email_user;
		this.password_user = password_user;
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

	public List<Project> getProjectlist() {
		return projectlist;
	}

	public void setProjectlist(List<Project> projectlist) {
		this.projectlist = projectlist;
	}

	public Integer getId_user() {
		return id_user;
	}
	
	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}
	
	public boolean ajoutProject (Project p){
		return projectlist.add(p);
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", name_user=" + name_user + ", surname_user=" + surname_user
				+ ", level_user=" + level_user + ", email_user=" + email_user + ", password_user=" + password_user
				+ ", projectlist=" + projectlist + "]";
	}
	
	public boolean ownsProject(Project p)
	{
		System.out.println("** ONLY-UP ** Looking for a match to project " + p.getId_project() +" ...");
		for(Project pl : this.projectlist)
		{
			System.out.println("** ONLY-UP ** User #"+this.getId_user()+" have project #"+pl.getId_project()+"... ");
			if(pl.getId_project().equals(p.getId_project()))
			{
				System.out.println(">>> MATCHED !");
				return true;
			}
		}
		return false;
	}
	
	public boolean removeProject(Project p)
	{
		return this.projectlist.remove(p);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_user == null) ? 0 : id_user.hashCode());
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
		User other = (User) obj;
		if (id_user == null) {
			if (other.id_user != null)
				return false;
		} else if (!id_user.equals(other.id_user))
			return false;
		return true;
	}

}
