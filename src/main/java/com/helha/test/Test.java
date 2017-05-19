package com.helha.test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.helha.entities.Project;
import com.helha.entities.User;

public class Test {
	public static void main(String[] args) {
		
		/*GestionProjectEJBRemote bean = null;
		try{		
			Context ctx = new InitialContext();
			bean = (GestionProjectEJBRemote)ctx.lookup("java:global/groupe6/GestionProjectEJB");
			System.out.println(bean.bonjour());
		}catch (NamingException e){
			e.printStackTrace();
		}*/
		
		User u1 = new User("Potter", "Harry", 1, "potter.harry@gmail.com", "test");
		User u2 = new User("Weasley", "Ron", 1, "weasley.ron@gmail.com", "test");
		User u3 = new User("Granger", "Hermione", 1, "granger.hermione@gmail.com", "test");
		User u4 = new User("Albus", "Dumbledore", 1, "albus.dumbledore@gmail.com", "test");
		
		Project p1 = new Project("Sort de Crache-Limaces", "Sortilège", 50.20, "Sotilège de cracha de limace", 30, "2016-01-18");
		Project p2 = new Project("Animagi", "Metamorphose", 1000.50, "Sortilège de metamorphose", 120, "2016-01-18");
		Project p3 = new Project("leviosa", "Sortilège", 750.30, "Sortilège de Lévitation", 60, "2016-01-18");
		Project p4 = new Project("Filtre d'amour", "Potion", 1000, "Filtre d'amour très puisant", 180, "2016-01-22");
		Project p5 = new Project("Scroutt à pétard", "Créature", 7500, "Le processus d'hybridation utilisé par Rubeus Hagrid est inconnu de tous.", 200, "2016-01-23");
		Project p6 = new Project("Le Quidditch à travers les âges", "Livre", 50.30, "Super livre sur le Quidditch", 10, "2016-01-24");
		Project p7 = new Project("Avadakedrava", "Magie Noir", 100000.99, "Sort qui permet de tuer quelqu'un", 600, "2016-01-25");
		
		
		List<Project>listProjet1 = new ArrayList<>();
		listProjet1.add(p1);
		List<Project>listProjet2 = new ArrayList<>();
		listProjet2.add(p2);
		List<Project>listProjet3 = new ArrayList<>();
		listProjet3.add(p3);
		List<Project>listProjet4 = new ArrayList<>();
		listProjet3.add(p4);
		List<Project>listProjet5 = new ArrayList<>();
		listProjet3.add(p5);
		List<Project>listProjet6 = new ArrayList<>();
		listProjet3.add(p6);
		List<Project>listProjet7 = new ArrayList<>();
		listProjet3.add(p7);
		
		u1.setProjectlist(listProjet1);
		u2.setProjectlist(listProjet2);
		u3.setProjectlist(listProjet3);
		u4.setProjectlist(listProjet4);
		u1.setProjectlist(listProjet5);
		u2.setProjectlist(listProjet6);
		u3.setProjectlist(listProjet7);
		
		List<String>listUser = new ArrayList<>();
		listUser.add(u2.getEmail_user());
		listUser.add(u3.getEmail_user());
		
		p1.setContributors_projects(listUser);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pgroupe6");
		EntityManager em = emf.createEntityManager();
//		EntityTransaction tx = em.getTransaction();
//		
//		tx.begin();
//		
//		em.persist(p7);
//		em.persist(p6);
//		em.persist(p5);
//		em.persist(p4);
//		em.persist(p2);
//		em.persist(p3);
//		em.persist(p1);
//		em.persist(u2);
//		em.persist(u3);
//		em.persist(u1);
//		em.persist(u4);
//		
//		tx.commit();
//		em.close();
//		emf.close();
	}
}
