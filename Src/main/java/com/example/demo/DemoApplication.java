package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	/*
	 * Pour lancer tomcat en ligne de commande :
	 *
	 * Pour lancer le serveur de BDD en ligne de commande:
	 * - cd dans le dossier contenant hsqldb.jar
	 * - lancer la commande : java -cp hsqldb.jar org.hsqldb.server.Server
	 * --database.0 file:firstBDD --dbname.0 firstBDD
	 *
	 * Pour lancer l'interface graphique de BDD :
	 * - se placer ds dossier contenant hsqldb.jar
	 * - java -cp hsqldb.jar org.hsqldb.util.DatabaseManagerSwing
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
