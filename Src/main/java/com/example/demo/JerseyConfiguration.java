package com.example.demo;

import com.example.demo.voyages.resource.VoyageResource;
import com.example.demo.personnes.resource.PersonneResource;
import com.example.demo.destinations.DestinationResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("api")

	
	public JerseyConfiguration() {
		register(PersonneResource.class);
		register(VoyageResource.class);
		register(DestinationResource.class);
		register(CORSResponseFilter.class);

		// La servlet de jersey map toutes les url /* donc impossible de servir du contenu statique via la servlet par défaut de sprign boot.
		
		property(ServletProperties.FILTER_FORWARD_ON_404, true);
	}
}
