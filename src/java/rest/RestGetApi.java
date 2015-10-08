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
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
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
public class RestGetApi {

    @Context
    private UriInfo context;

    private Gson gson;
    private AdderFacade adderFacade;
    private DeleteFacade deleteFacade;
    private ServiceFacade serviceFacade;
    private EntityManagerFactory emf;

    public RestGetApi() {
        emf = Persistence.createEntityManagerFactory("CA2PU");

        gson = new GsonBuilder().setPrettyPrinting().create();
        adderFacade = new AdderFacade(emf);
        deleteFacade = new DeleteFacade(emf);
        serviceFacade = new ServiceFacade(emf);

    }

    
    @GET
    @Path("/zipcodes")
    @Produces("application/json")
    public String getZipCodes(){
    
        List<CityInfo> cities = serviceFacade.getCityInfoList();
        JsonArray jsonArray = new JsonArray();
        for (CityInfo city : cities) {
            JsonObject json = new JsonObject();
            json.addProperty("ZipCode", city.getZipCode());
            jsonArray.add(json);
        }
        return gson.toJson(jsonArray);
    }
    
    @GET
    @Path("/city/{name}")
    @Produces("application/json")
    public String getPeopleFromCity(@PathParam("name")String name){
        List<Person> people = serviceFacade.getPeopleFromCity(name);
        JsonArray jsonArray = new JsonArray();
        for (Person p : people) {
            JsonObject json = new JsonObject();
            json.addProperty("id", p.getId());
            json.addProperty("name", new String(p.getFirstName() + " " + p.getLastName()));
            json.addProperty("email", p.getEmail());

            List<Phone> phones = p.getPhones();
            JsonArray phoneArray = new JsonArray();
            for (Phone phone : phones) {
                JsonObject phoneJson = new JsonObject();
                phoneJson.addProperty("number", phone.getNumber());
                phoneJson.addProperty("description", phone.getDescription());
                phoneArray.add(phoneJson);
            }

            json.add("phones", phoneArray);
            jsonArray.add(json);
        }

        return gson.toJson(jsonArray);
    }
    
    
    
    @GET
    @Path("/phone/{number}")
    @Produces("application/json")
    public String getPersonFromPhone(@PathParam("number")Integer number){
        Person p = serviceFacade.getPersonFromPhone(number);
        
        JsonObject json = new JsonObject();
        json.addProperty("firstName", p.getFirstName());
        json.addProperty("lastName", p.getLastName());
        json.addProperty("email", p.getEmail());

        List<Phone> phones = p.getPhones();
        JsonArray phoneArray = new JsonArray();
        for (Phone phone : phones) {
            JsonObject phoneJson = new JsonObject();
            phoneJson.addProperty("number", phone.getNumber());
            phoneJson.addProperty("description", phone.getDescription());
            phoneArray.add(phoneJson);
        }

        json.add("phones", phoneArray);
        json.addProperty("strret", p.getAddress().getStreet());
        json.addProperty("additionalInfo", p.getAddress().getAdditionalInfo());
        json.addProperty("zipcode", p.getAddress().getCityInfo().getZipCode());
        json.addProperty("city", p.getAddress().getCityInfo().getCity());

        return gson.toJson(json);
    }
    
    @GET
    @Path("/hobby/{hobby}")
    @Produces("application/json")
    public String getPeopleFormHobby(@PathParam("hobby") String hobby) {
        List<Hobby> hobbies = serviceFacade.getHobbies();
        int hobbyId = 0;
        for (Hobby h : hobbies) {
            if (h.getName().contains(hobby)) {
                hobbyId = h.getId()-1;
            }
        }
        List<Person> people = serviceFacade.getPeopleFromHobby(hobbies.get(hobbyId));
        JsonArray jsonArray = new JsonArray();
        for (Person p : people) {
        JsonObject json = new JsonObject();
        json.addProperty("id", p.getId());
        json.addProperty("name", new String(p.getFirstName() + " " + p.getLastName()));
        json.addProperty("email", p.getEmail());
        
        List<Hobby> phobbies = p.getHobbies();
        JsonArray hobbyArray = new JsonArray();
        for (Hobby h : phobbies) {
            JsonObject phoneJson = new JsonObject();
            phoneJson.addProperty("name", h.getName());
            phoneJson.addProperty("description", h.getDescription());
            hobbyArray.add(phoneJson);
        }
        json.add("hobby", hobbyArray);
        jsonArray.add(json);
        }
        return gson.toJson(jsonArray);
    }

    @GET
    @Path("/complete/{id}")
    @Produces("application/json")
    public String getCompleteId(@PathParam("id") int id) throws PersonNotFoundException {
        List<Person> people = serviceFacade.getPeople();

        if (people.isEmpty()) {
            throw new PersonNotFoundException("No people in database");
        }
        Person p = people.get(id);

        JsonObject json = new JsonObject();
        json.addProperty("firstName", p.getFirstName());
        json.addProperty("lastName", p.getLastName());
        json.addProperty("email", p.getEmail());

        List<Phone> phones = p.getPhones();
        JsonArray phoneArray = new JsonArray();
        for (Phone phone : phones) {
            JsonObject phoneJson = new JsonObject();
            phoneJson.addProperty("number", phone.getNumber());
            phoneJson.addProperty("description", phone.getDescription());
            phoneArray.add(phoneJson);
        }

        json.add("phones", phoneArray);
        json.addProperty("strret", p.getAddress().getStreet());
        json.addProperty("additionalInfo", p.getAddress().getAdditionalInfo());
        json.addProperty("zipcode", p.getAddress().getCityInfo().getZipCode());
        json.addProperty("city", p.getAddress().getCityInfo().getCity());

        return gson.toJson(json);
    }

    @GET
    @Path("/complete")
    @Produces("application/json")
    public String getComplete() throws PersonNotFoundException {

        List<Person> people = serviceFacade.getPeople();

        if (people.isEmpty()) {
            throw new PersonNotFoundException("No people in database");
        }

        JsonArray jsonArray = new JsonArray();
        for (Person p : people) {
            JsonObject json = new JsonObject();
            json.addProperty("firstName", p.getFirstName());
            json.addProperty("lastName", p.getLastName());
            json.addProperty("email", p.getEmail());

            List<Phone> phones = p.getPhones();
            JsonArray phoneArray = new JsonArray();
            for (Phone phone : phones) {
                JsonObject phoneJson = new JsonObject();
                phoneJson.addProperty("number", phone.getNumber());
                phoneJson.addProperty("description", phone.getDescription());
                phoneArray.add(phoneJson);
            }

            json.add("phones", phoneArray);
            json.addProperty("strret", p.getAddress().getStreet());
            json.addProperty("additionalInfo", p.getAddress().getAdditionalInfo());
            json.addProperty("zipcode", p.getAddress().getCityInfo().getZipCode());
            json.addProperty("city", p.getAddress().getCityInfo().getCity());
            jsonArray.add(json);
        }

        return gson.toJson(jsonArray);
    }

    @GET
    @Path("/contactinfo/{id}")
    @Produces("application/json")
    public String getContactinfoId(@PathParam("id") int id) throws PersonNotFoundException {
        List<Person> people = serviceFacade.getPeople();

        if (people.isEmpty()) {
            throw new PersonNotFoundException("No people in database");
        }
        Person p = people.get(id);

        JsonObject json = new JsonObject();
        json.addProperty("id", p.getId());
        json.addProperty("name", new String(p.getFirstName() + " " + p.getLastName()));
        json.addProperty("email", p.getEmail());

        List<Phone> phones = p.getPhones();
        JsonArray phoneArray = new JsonArray();
        for (Phone phone : phones) {
            JsonObject phoneJson = new JsonObject();
            phoneJson.addProperty("number", phone.getNumber());
            phoneJson.addProperty("description", phone.getDescription());
            phoneArray.add(phoneJson);
        }

        json.add("phones", phoneArray);

        return gson.toJson(json);
    }

    @GET
    @Path("/contactinfo")
    @Produces("application/json")
    public String getContactInfo() throws PersonNotFoundException {

        List<Person> people = serviceFacade.getPeople();

        if (people.isEmpty()) {
            throw new PersonNotFoundException("No people in database");
        }

        JsonArray jsonArray = new JsonArray();
        for (Person p : people) {
            JsonObject json = new JsonObject();
            json.addProperty("id", p.getId());
            json.addProperty("name", new String(p.getFirstName() + " " + p.getLastName()));
            json.addProperty("email", p.getEmail());

            List<Phone> phones = p.getPhones();
            JsonArray phoneArray = new JsonArray();
            for (Phone phone : phones) {
                JsonObject phoneJson = new JsonObject();
                phoneJson.addProperty("number", phone.getNumber());
                phoneJson.addProperty("description", phone.getDescription());
                phoneArray.add(phoneJson);
            }

            json.add("phones", phoneArray);
            jsonArray.add(json);
        }

        return gson.toJson(jsonArray);
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

}
