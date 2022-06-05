package com.example.demo;

import java.io.IOException;
import java.nio.file.Paths;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.boot.SpringApplication;





@SpringBootApplication
public class CrudExampleApplication {
	
	public static void main(String[] args) throws IOException {
  
	    SpringApplication.run(CrudExampleApplication.class, args);
	
	
	   //Permet d'initialiser la liste des pays 
	    PaysResource.BDD = new ObjectMapper().readValue(
			    Paths.get("C:\\Users\\pc\\OneDrive\\Documents\\TECHNO WEB\\country.json").toFile(),
			    new TypeReference<>()  {
				
			    }		
	   );
			
	
   }
}
