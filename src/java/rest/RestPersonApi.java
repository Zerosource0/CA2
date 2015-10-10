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
import deploy.DeploymentConfiguration;
import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.Person;
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
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author marcj_000
 */
@Path("person")
public class RestPersonApi {

    @Context
    private UriInfo context;

    private Gson gson;
    private AdderFacade adderFacade;
    private DeleteFacade deleteFacade;
    private ServiceFacade serviceFacade;
    private EntityManagerFactory emf;

    public RestPersonApi() {
        emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);

        gson = new GsonBuilder().setPrettyPrinting().create();
        adderFacade = new AdderFacade(emf);
        deleteFacade = new DeleteFacade(emf);
        serviceFacade = new ServiceFacade(emf);

    }

    @GET
    @Path("/zipcodes")
    @Produces("application/json")
    public String getZipCodes() throws EntityNotFoundException {

        List<CityInfo> cities = serviceFacade.getCityInfoList();
        
        if (cities.isEmpty()) {
            throw new EntityNotFoundException("No ZipCodes exists in the databse");
        }
        
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
    public String getPeopleFromCity(@PathParam("name") String name) throws EntityNotFoundException {
        List<Person> people = serviceFacade.getPeopleFromCity(name);
        
        if (people.isEmpty()) {
            throw new EntityNotFoundException("No people named: " + name + " exists in the database.");
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

    @GET
    @Path("/phone/{number}")
    @Produces("application/json")
    public String getPersonFromPhone(@PathParam("number") Integer number) throws EntityNotFoundException {
        Person p = serviceFacade.getPersonFromPhone(number);
        if (p.equals(null)) {
            throw new EntityNotFoundException("No person with the number: " + number + " exists in the database");
        }
        
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
        json.addProperty("street", p.getAddress().getStreet());
        json.addProperty("additionalInfo", p.getAddress().getAdditionalInfo());
        json.addProperty("zipcode", p.getAddress().getCityInfo().getZipCode());
        json.addProperty("city", p.getAddress().getCityInfo().getCity());

        return gson.toJson(json);
    }

    @GET
    @Path("/hobby/{hobby}")
    @Produces("application/json")
    public String getPeopleFormHobby(@PathParam("hobby") String hobby) throws EntityNotFoundException {
        List<Hobby> hobbies = serviceFacade.getHobbies();
        if (hobbies.isEmpty()) {
            throw new EntityNotFoundException("No hobbies exists in the database");
        }
        
        int hobbyId = 0;
        for (Hobby h : hobbies) {
            if (h.getName().contains(hobby)) {
                hobbyId = h.getId() - 1;
            }
        }
        List<Person> people = serviceFacade.getPeopleFromHobby(hobbies.get(hobbyId));
        if (people.isEmpty()) {
            throw new EntityNotFoundException("No people with the hobby: " + hobby + " exists in the databse");
        }
        
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
    public String getCompleteId(@PathParam("id") int id) throws EntityNotFoundException {
        List<Person> people = serviceFacade.getPeople();
        
        boolean idExists = false;
        if (people.isEmpty()) {
            throw new EntityNotFoundException("No people in database");
        } 
            for (Person p : people) {
                if(p.getId() == id) idExists = true; 
            }
        
        if(!idExists) throw new EntityNotFoundException("No person with id: " + id + " exists in the database");
        Person p = people.get(id - 1);

        JsonObject json = new JsonObject();
        json.addProperty("id", p.getId());
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
        
                //json.addProperty("city", p.getAddress().getCityInfo().getCity());
           
                System.out.println(p.getAddress().getStreet());
        
                json.addProperty("street", p.getAddress().getStreet());

            try {
                json.addProperty("additionalInfo", p.getAddress().getAdditionalInfo());
            } catch (Exception e) {
            }
            try {
                json.addProperty("zipcode", p.getAddress().getCityInfo().getZipCode());
            } catch (Exception e) {
            }
        return gson.toJson(json);
    }

    @GET
    @Path("/complete")
    @Produces("application/json")
    public String getComplete() throws EntityNotFoundException {

        List<Person> people = serviceFacade.getPeople();

        if (people.isEmpty()) {
            throw new EntityNotFoundException("No people in database");
        }

        JsonArray jsonArray = new JsonArray();
        for (Person p : people) {
            JsonObject json = new JsonObject();
            json.addProperty("id", p.getId());
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
            

            jsonArray.add(json);
        }

        return gson.toJson(jsonArray);
    }

    @GET
    @Path("/contactinfo/{id}")
    @Produces("application/json")
    public String getContactinfoId(@PathParam("id") int id) throws EntityNotFoundException {
        List<Person> people = serviceFacade.getPeople();

        boolean idExists = false;
        if (people.isEmpty()) {
            throw new EntityNotFoundException("No people in database");
        } 
            for (Person p : people) {
                if(p.getId() == id) idExists = true; 
            }
        
        if(!idExists) throw new EntityNotFoundException("No person with id: " + id + " exists in the database");
        Person p = people.get(id - 1);

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
    public String getContactInfo() throws EntityNotFoundException {

        List<Person> people = serviceFacade.getPeople();

        if (people.isEmpty()) {
            throw new EntityNotFoundException("The database is empty :(");
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
    @Produces("application/json")
    @Consumes("application/json")
    @Path("{id}")
    public String editPerson(@PathParam("id") Integer id, String content) throws EntityNotFoundException {
        JsonObject newPerson = new JsonParser().parse(content).getAsJsonObject();
        
        
        List<Person> people = serviceFacade.getPeople();
        boolean idExists = false;
        if (people.isEmpty()) {
            throw new EntityNotFoundException("No people in database");
        } 
            for (Person p : people) {
                if(p.getId() == id) idExists = true; 
            }
        
        if(!idExists) throw new EntityNotFoundException("No person with id: " + id + " exists in the database");
        
        Person p = new Person();
        p.setId(id);
        p.setFirstName(newPerson.get("firstName").getAsString());
        p.setLastName(newPerson.get("lastName").getAsString());
        p.setEmail(newPerson.get("email").getAsString());

        CityInfo cityInfo = new CityInfo();
        cityInfo.setCity(newPerson.get("city").getAsString());
        cityInfo.setZipCode(newPerson.get("zipCode").getAsInt());

        Address address = new Address();
        address.setStreet(newPerson.get("street").getAsString());
        address.setAdditionalInfo(newPerson.get("additionalInfo").getAsString());
        cityInfo.addAddress(address);
        address.setCityInfo(cityInfo);
        adderFacade.editCityInfo(cityInfo);

        Phone phone = new Phone(newPerson.get("phone").getAsInt(), newPerson.get("description").getAsString());
        p.addPhone(phone);

        adderFacade.editPerson(p);
        phone.addInfoEntity(p);
        address.addInfoEntity(p);
        p.setAddress(address);

        JsonObject returnJson = new JsonObject();
        returnJson.addProperty("id", p.getId());
        returnJson.addProperty("firstName", p.getFirstName());
        returnJson.addProperty("lastName", p.getLastName());
        returnJson.addProperty("email", p.getEmail());
        returnJson.addProperty("city", p.getAddress().getCityInfo().getCity());
        returnJson.addProperty("street", p.getAddress().getStreet());
        returnJson.addProperty("phone", p.getPhones().toString());
        return gson.toJson(returnJson);
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public String addPersonComplex(String content) {
        JsonObject newPerson = new JsonParser().parse(content).getAsJsonObject();
        Person p = new Person();
        p.setFirstName(newPerson.get("firstName").getAsString());
        p.setLastName(newPerson.get("lastName").getAsString());
        p.setEmail(newPerson.get("email").getAsString());

        CityInfo cityInfo = new CityInfo();
        cityInfo.setCity(newPerson.get("city").getAsString());
        cityInfo.setZipCode(newPerson.get("zipCode").getAsInt());

        Address address = new Address();
        address.setStreet(newPerson.get("street").getAsString());
        address.setAdditionalInfo(newPerson.get("additionalInfo").getAsString());
        Phone phone = new Phone(newPerson.get("phone").getAsInt(), newPerson.get("description").getAsString());
        p.addPhone(phone);
        phone.addInfoEntity(p);
        p.setAddress(address);
        address.addInfoEntity(p);
        cityInfo.addAddress(address);
        address.setCityInfo(cityInfo);
        
        adderFacade.addCityInfo(cityInfo);
        
        
        //adderFacade.addPerson(p);


        JsonObject returnJson = new JsonObject();
        returnJson.addProperty("id", p.getId());
        returnJson.addProperty("firstName", p.getFirstName());
        returnJson.addProperty("lastName", p.getLastName());
        returnJson.addProperty("email", p.getEmail());
        returnJson.addProperty("city", p.getAddress().getCityInfo().getCity());
        returnJson.addProperty("street", p.getAddress().getStreet());
        returnJson.addProperty("phone", p.getPhones().toString());
        return gson.toJson(returnJson);
    }

    @DELETE
    @Path("/person/{id}")
    @Produces("application/json")
    public String deletePerson(@PathParam("id") int id) throws EntityNotFoundException {
        Person p = deleteFacade.deletePerson(id);
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
}
