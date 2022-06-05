package com.example.demo.destinations;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("destinations")
public class DestinationResource {
    static List<Destination> BDD = new ArrayList();

    // Ajouter
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void AjoutDestination(List<Destination> L) {
        for (Destination D : L)
            BDD.add(D);
    }

    // Lister
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Destination> Lister() {

        return BDD;
    }

    // rechercher
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Destination RechercheD(@PathParam("id") int id) {
        return BDD.get(id);
    }

    // modifier
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Destination UpdateD(@PathParam("id") int id, Destination d) {
        return BDD.set(id, d);
    }

    // Supprimer
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Destination DeleteD(@PathParam("id") int id) {
        return BDD.remove(id);
    }

}
