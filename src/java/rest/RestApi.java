/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Person;
import entity.Phone;
import facade.AdderFacade;
import facade.DeleteFacade;
import facade.ServiceFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author marcj_000
 */
@Path("person")
public class RestApi {

    @Context
    private UriInfo context;

    private Gson gson;
    private AdderFacade adderFacade;
    private DeleteFacade deleteFacade;
    private ServiceFacade serviceFacade;
    private EntityManagerFactory emf;
    
    
    public RestApi() {
       emf = Persistence.createEntityManagerFactory("CA2PU") ;
       
       gson = new GsonBuilder().setPrettyPrinting().create();
       adderFacade = new AdderFacade(emf);
       deleteFacade = new DeleteFacade(emf);
       serviceFacade = new ServiceFacade(emf);
       
    }


    @GET
    @Path("/complete")
    @Produces("application/json")
    public String getJson() {
        
        List<Person> people = serviceFacade.getPeople();
        System.out.println("PEOPLE SIZE: " + people.size());
        JsonArray jsonArray = new JsonArray();
        for (Person p : people) {
            JsonObject json = new JsonObject();
            json.addProperty("firstName", p.getFirstName());
            json.addProperty("lastName", p.getLastName());
            json.addProperty("email", p.getEmail());
            List<Phone> phones = p.getPhones();  
            for (Phone phone : phones) {
                String s = phone.getNumber()+", "+phone.getDescription();
                json.addProperty("phones", s);
            }
            json.addProperty("strret", p.getAddress().getStreet());
            json.addProperty("additionalInfo", p.getAddress().getAdditionalInfo());
            json.addProperty("zipcode", p.getAddress().getCityInfo().getZipCode());
            json.addProperty("city", p.getAddress().getCityInfo().getCity());
            jsonArray.add(json);
        }        
        
        return gson.toJson(jsonArray);
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    
}
