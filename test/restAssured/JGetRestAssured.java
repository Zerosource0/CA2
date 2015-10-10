/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restAssured;

import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.parsing.Parser;
import javax.ws.rs.core.MediaType;
import static org.hamcrest.Matchers.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import tester.Tester;

/**
 *
 * @author williambech
 */
public class JGetRestAssured {
    
       public JGetRestAssured() {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseURI = "http://localhost:8080/CA2";
        defaultParser = Parser.JSON;
        basePath = "/api";
    }

    //GET PERSON TESTS
    @Test
    public void getPerson() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/person/complete/1").
                then().
                    statusCode(200).
                    body("firstName",equalTo("Adam")).
                    body("lastName",equalTo("Lewandowski"))
                ;
                
    }

    @Test
    public void getAllPeople() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/person/complete").
                then().
                statusCode(200).
                body("firstName", hasItems("Adam","Kurt")).
                body("lastName", hasItems("Lewandowski","Wonnegut4"));
    }
    
    @Test
    public void getZips() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("person/zipcodes").
                then().
                statusCode(200).
                body("ZipCode", hasItems(647, 5346));
    }
    
    @Test
    public void getPeopleFromCity() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/person/city/New York").
                then().
                statusCode(200).
                body("name", hasItems("Kurt Wonnegut4"));
    }

    @Test
    public void getPersonFromPhone() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/person/phone/12345678").
                then().
                statusCode(200). 
                body("firstName", equalTo("Kurt")).
                body("lastName", equalTo("Wonnegut4"));
    }

    @Test
    public void getPeopleFromHobby() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/person/hobby/drugs").
                then().
                statusCode(200).
                body("name", hasItems("Kurt Wonnegut4"));
    }

    @Test
    public void getContactInfoId() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/person/contactinfo/1").
                then().
                statusCode(200).
                body("id", equalTo(2)). 
                body("name", equalTo("Adam Lewandowski")). 
                body("email", equalTo("adam@kurva.com")). 
                body("phones.number", hasItems(92375678));
    }

    @Test
    public void getContactInfo() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/person/contactinfo").
                then().
                statusCode(200).
                body("id", hasItems(2,3)). 
                body("name", hasItems("Adam Lewandowski", "Kurt Wonnegut4")). 
                body("email", hasItems("adam@kurva.com","kurt@wonnegut.com"));
               // body("phones.number", hasItems(92375678, 12345678));
        
    }
    



}
    

