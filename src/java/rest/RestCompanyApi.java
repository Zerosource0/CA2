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
import com.google.gson.JsonParser;
import entity.Company;
import entity.Phone;
import exception.EntityNotFoundException;
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
import javax.ws.rs.POST;
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

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String addCompany(String content) {
        JsonObject newCompany = new JsonParser().parse(content).getAsJsonObject();
        Company c = new Company();
        c.setName(newCompany.get("name").getAsString());
        c.setDescription(newCompany.get("description").getAsString());
        c.setCvr(Integer.parseInt(newCompany.get("cvr").getAsString()));
        c.setNumEmployees(Integer.parseInt(newCompany.get("numEmployees").getAsString()));
        c.setMarketValue(Long.parseLong(newCompany.get("marketValue").getAsString()));
        adderFacade.addCompany(c);
        JsonObject returnJson = new JsonObject();
        returnJson.addProperty("name", c.getName());
        returnJson.addProperty("description", c.getDescription());
        returnJson.addProperty("cvr", c.getCvr());
        returnJson.addProperty("numEmployees", c.getNumEmployees());
        returnJson.addProperty("marketValue", c.getMarketValue());
        return gson.toJson(returnJson);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("{id}")
    public String editCompany(@PathParam("id")Integer id, String content) {
        JsonObject newCompany = new JsonParser().parse(content).getAsJsonObject();
        Company c = new Company();
        c.setId(id);
        c.setName(newCompany.get("name").getAsString());
        c.setDescription(newCompany.get("description").getAsString());
        c.setCvr(Integer.parseInt(newCompany.get("cvr").getAsString()));
        c.setNumEmployees(Integer.parseInt(newCompany.get("numEmployees").getAsString()));
        c.setMarketValue(Long.parseLong(newCompany.get("marketValue").getAsString()));
        adderFacade.editCompany(c);
        JsonObject returnJson = new JsonObject();
        returnJson.addProperty("name", c.getName());
        returnJson.addProperty("description", c.getDescription());
        returnJson.addProperty("cvr", c.getCvr());
        returnJson.addProperty("numEmployees", c.getNumEmployees());
        returnJson.addProperty("marketValue", c.getMarketValue());
        return gson.toJson(returnJson);
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getCompanyById(@PathParam("id") int id) {
        List<Company> companies = serviceFacade.getCompanies();

        if (companies.isEmpty() || companies.get(id - 1) == null) {
            //Thow exception
        }
        Company c = companies.get(id - 1);
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
    public String deleteCompany(@PathParam("id") int id) throws EntityNotFoundException {
        Company c = deleteFacade.deleteCompany(id);
        return gson.toJson(c);
    }
}
