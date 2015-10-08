/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Company;
import entity.Person;
import exception.PersonNotFoundException;
import facade.AdderFacade;
import facade.DeleteFacade;
import facade.ServiceFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author marcj_000
 */
@Path("delete")
public class RestDeleteApi {

    @Context
    private UriInfo context;

    private static Gson gson;
    private AdderFacade adderFacade;
    private DeleteFacade deleteFacade;
    private ServiceFacade serviceFacade;
    private EntityManagerFactory emf;

    public RestDeleteApi() {
        emf = Persistence.createEntityManagerFactory("CA2PU");

        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        adderFacade = new AdderFacade(emf);
        deleteFacade = new DeleteFacade(emf);
        serviceFacade = new ServiceFacade(emf);

    }

    @DELETE
    @Path("/person/{id}")
    @Produces("application/json")
    public String deletePerson(@PathParam("id") int id) throws PersonNotFoundException {
        Person p = deleteFacade.deletePerson(id);
        return gson.toJson(p);
    }

    @DELETE
    @Path("/company/{id}")
    @Produces("application/json")
    public String deleteCompany(@PathParam("id") int id) throws PersonNotFoundException {
        Company c = deleteFacade.deleteCompany(id);
        return gson.toJson(c);
    }

}
