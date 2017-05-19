package com.helha.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/*Ne sert qu'a définir le chemin des services web
 * Doit dériver RessourceConfig
*/
@ApplicationPath("services")
public class ApplicationConfig extends ResourceConfig {
	
	public ApplicationConfig() {
		packages("com.helha.services"); // Nom du package qui implémente les services
	}

}
