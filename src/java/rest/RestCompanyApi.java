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
import entity.Company;
import entity.Phone;
import exception.PersonNotFoundException;
import facade.AdderFacade;
import facade.DeleteFacade;
import facade.ServiceFacade;
import java.util.List;
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
@Path("company")
public class RestCompanyApi {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestPostService
     */
    private Gson gson;
    private AdderFacade adderFacade;
    private DeleteFacade deleteFacade;
    private ServiceFacade serviceFacade;
    private EntityManagerFactory emf;

    public RestCompanyApi() {
        emf = Persistence.createEntityManagerFactory("CA2PU");

        gson = new GsonBuilder().setPrettyPrinting().create();
        adderFacade = new AdderFacade(emf);
        deleteFacade = new DeleteFacade(emf);
        serviceFacade = new ServiceFacade(emf);

    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getCompanyById(@PathParam("id") int id) {
        List<Company> companies = serviceFacade.getCompanies();

        if (companies.isEmpty() || companies.get(id-1) == null) {
            //Thow exception
        }
        Company c = companies.get(id-1);
        JsonObject json = new JsonObject();
        json.addProperty("name", c.getName());
        json.addProperty("description", c.getDescription());
        json.addProperty("cvr", c.getCvr());
        json.addProperty("email", c.getEmail());
        
        
        List<Phone> phones = c.getPhones();
        JsonArray phoneArray = new JsonArray();
        for (Phone phone : phones) {
            JsonObject pJson = new JsonObject();
            pJson.addProperty("number", phone.getNumber());
            pJson.addProperty("decription", phone.getDescription());
        }
        json.add("phones", phoneArray);
        json.addProperty("street", c.getAddress().getStreet());
        json.addProperty("additionalInfo", c.getAddress().getAdditionalInfo());
        json.addProperty("city", c.getAddress().getCityInfo().getCity());
        json.addProperty("numEmployees", c.getNumEmployees());
        json.addProperty("marketValue", c.getMarketValue());
        
        return gson.toJson(json);
    }

    @GET
    @Produces("application/json")
    public String getCompanies() {
        List<Company> companies = serviceFacade.getCompanies();

        if (companies.isEmpty()) {
            //Thow exception
        }
        
        JsonArray jsonArray = new JsonArray();
        
        for (Company c : companies) {        
        JsonObject json = new JsonObject();
        json.addProperty("name", c.getName());
        json.addProperty("description", c.getDescription());
        json.addProperty("cvr", c.getCvr());
        json.addProperty("email", c.getEmail());
        
        
        List<Phone> phones = c.getPhones();
        JsonArray phoneArray = new JsonArray();
        for (Phone phone : phones) {
            JsonObject pJson = new JsonObject();
            pJson.addProperty("number", phone.getNumber());
            pJson.addProperty("decription", phone.getDescription());
        }
        json.add("phones", phoneArray);
        json.addProperty("street", c.getAddress().getStreet());
        json.addProperty("additionalInfo", c.getAddress().getAdditionalInfo());
        json.addProperty("city", c.getAddress().getCityInfo().getCity());
        json.addProperty("numEmployees", c.getNumEmployees());
        json.addProperty("marketValue", c.getMarketValue());
        jsonArray.add(json);
        }
        
        return gson.toJson(jsonArray);
    }
    
    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public String deleteCompany(@PathParam("id") int id) throws PersonNotFoundException {
        Company c = deleteFacade.deleteCompany(id);
        return gson.toJson(c);
    }
}
