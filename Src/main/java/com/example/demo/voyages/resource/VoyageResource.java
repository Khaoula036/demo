package com.example.demo.voyages.resource;

import com.example.demo.voyages.bdd.Voyage;
import com.example.demo.voyages.bdd.VoyageRepository;
import com.example.demo.personnes.process.Personne;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("Voyages")
public class VoyageResource {
    @Autowired
    private VoyageRepository voyageRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Voyage> listerVoyages() {
        List<Voyage> voyage = new ArrayList<>();
        voyageRepository.findAll().forEach(voyages::add);
        return voyages;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Voyage creeerVoyage(Voyage v) {
        return VoyageRepository.save(v);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteVoyage(@PathParam("id") Long id) {
        if (voyageRepository.findById(id).isPresent()) {
            try {
                voyageRepository.deleteById(id);
            } catch (Exception e) {
                return Response.serverError().build();
            }
        }
        return Response.noContent().build();
    }
}
