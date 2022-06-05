package com.example.demo.personnes.resource;

import com.example.demo.voyages.bdd.Voyage;
import com.example.demo.voyages.bdd.VoyageRepository;
import com.example.demo.personnes.process.Personne;
import com.example.demo.personnes.process.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("personnes")
public class PersonneResource {
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private VoyageRepository voyageRepository;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Personne createPersonne(Personne p) {
		return personneRepository.save(p);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Personne> getAllPersonne() {
		List<Personne> personnes = new ArrayList<>();
		personneRepository.findAll().forEach(personnes::add);
		return personnes;
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Personne updateTotalyPersonne(@PathParam("id") Long id, Personne p) {
		p.setId(id);
		return personneRepository.save(p);
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePersonne(@PathParam("id") Long id) {
		if (personneRepository.findById(id).isPresent()) {
			personneRepository.deleteById(id);
		}
		return Response.noContent().build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getpersonneById(@PathParam("id") Long id) {
		Optional<Personne> p = personneRepository.findById(id);
		if (p.isPresent()) {
			return Response.ok(p.get()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	// PATCH /personnes/{id}
	public Response updateAge(@PathParam("id") Long id, Personne p) {
		int age = p.getAge();
		Optional<Personne> optional = personneRepository.findById(id);

		if (optional.isPresent()) {
			Personne pBDD = optional.get();
			pBDD.setAge(age);
			personneRepository.save(pBDD);
			return Response.ok(pBDD).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("{id}/voyages")
	@Produces(MediaType.APPLICATION_JSON)
	// GET /personnes/{id}/voyages
	public List<Livre> listerVoyages(@PathParam("id") Long id) {
		return personneRepository.findById(id).get().getVoyages();
	}

	@POST
	@Path("{idPersonne}/voyages")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLivreDejaExistant(@PathParam("idPersonne") Long idPersonne, VoyageInput voyages) {
		Optional<Personne> pOpt = personneRepository.findById(idPersonne);
		Optional<Voyage> lOpt = voyageRepository.findById(voyages.getIdVoyage());

		if (!pOpt.isPresent() || !lOpt.isPresent()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		Personne p = pOpt.get();
		Voyage v = vOpt.get();
		p.getVoyages().add(l);
		personneRepository.save(p);
		return Response.ok(p).build();
	}
}
