/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.parsing.Parser;
import javax.ws.rs.core.MediaType;
import static org.hamcrest.Matchers.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author williambech
 */
public class JRestAssured {

    public JRestAssured() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseURI = "http://localhost:8080/CA2";
        defaultParser = Parser.JSON;
        basePath = "/api/person";
    }

    //GET PERSON TESTS
    @Test
    public void getPerson() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/person/complete/1").
                then();
    }

    @Test
    public void getAllPeople() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/person/complete").
                then().
                body("person", equalTo(""));
    }
    
    @Test
    public void getPeopleFromContact() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/person/contactinfo").
                then().
                body("person", equalTo("fs"));
    }

    @Test
    public void getPersonFromContact() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/person/contactinfo/1").
                then();
    }

    @Test
    public void getCompany() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get().
                then().
                body("company", equalTo("fs"));
    }

    @Test
    public void getHobby() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get().
                then().
                body("hobby", equalTo("fs"));
    }

    @Test
    public void getPhone() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get().
                then().
                body("phone", equalTo(123));
    }

    @Test
    public void getAddress() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get().
                then().
                body("address", equalTo(""));
    }

    //POST TEST
    @Test
    public void postAddress() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                body("{\"address\":\"this is so wise\"}").
                when().
                post().
                then(). 
                statusCode(200);
    }

    @Test
    public void postPerson() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                body("{\"person\":\"this is so wise\"}").
                when().
                post().
                then(). 
                statusCode(200);
    }

    @Test
    public void postCompany() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                body("{\"company\":\"this is so wise\"}").
                when().
                post().
                then(). 
                statusCode(200);
    }

    @Test
    public void postHobby() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                body("{\"hibby\":\"this is so wise\"}").
                when().
                post().
                then(). 
                statusCode(200);
    }

    @Test
    public void postPhone() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                body("{\"phone\":\"this is so wise\"}").
                when().
                post().
                then(). 
                statusCode(200);
    }

    //PUT TEST
    @Test
    public void putAddress() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                body("{\"address\":\"this is so wise\"}").
                when().
                put().
                then(). 
                statusCode(200);
    }

    @Test
    public void putPerson() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                body("{\"person\":\"this is so wise\"}").
                when().
                put().
                then(). 
                statusCode(200);
    }

    @Test
    public void putCompany() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                body("{\"company\":\"this is so wise\"}").
                when().
                put().
                then(). 
                statusCode(200);
    }

    @Test
    public void putHobby() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                body("{\"hobby\":\"this is so wise\"}").
                when().
                put().
                then(). 
                statusCode(200);
    }

    @Test
    public void putPhone() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                body("{\"phone\":\"this is so wise\"}").
                when().
                put().
                then().
                statusCode(200);   
    }

    //DELETE TEST
    @Test
    public void deleteAddress() {
        given().
                when().
                delete().
                then(). 
                statusCode(200);
    }

    @Test
    public void deletePerson() {
        given().
                when().
                delete().
                then(). 
                statusCode(200);
    }

    @Test
    public void deleteCompany() {
        given().
                when().
                delete().
                then(). 
                statusCode(200);
    }

    @Test
    public void deleteHobby() {
        given().
                when().
        delete().
        then(). 
                statusCode(200);
    }

    @Test
    public void deletePhone() {
        given().
                when().
        delete().
        then(). 
                statusCode(200);
    }

}
