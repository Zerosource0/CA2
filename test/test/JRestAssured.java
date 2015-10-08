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
                    body("firstName",equalTo("Kurt")).
                    body("lastName",equalTo("Wonnegut4"))
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
                body("name", hasItems("Adam Lewandowski", "Kurt Wonnegut4"));
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
                body("id", equalTo(5)). 
                body("name", equalTo("Kurt Wonnegut4")). 
                body("email", equalTo("kurt@wonnegut.com")). 
                body("phones.number", hasItems(87654321,12345678));
    }

    @Test
    public void getContactInfo() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                when().
                get("/person/contactinfo").
                then().
                statusCode(200).
                body("id", hasItems(4,5)). 
                body("name", hasItems("Kurt Wonnegut4","Adam Lewandowski")). 
                body("email", hasItems("kurt@wonnegut.com")).
                body("phones.number", hasItems(92375678,87654321,12345678));
    }
    

//
//    //POST TEST
//    @Test
//    public void postAddress() {
//        given().
//                contentType(MediaType.APPLICATION_JSON).
//                body("{\"address\":\"this is so wise\"}").
//                when().
//                post().
//                then(). 
//                statusCode(200);
//    }
//
//    @Test
//    public void postPerson() {
//        given().
//                contentType(MediaType.APPLICATION_JSON).
//                body("{\"person\":\"this is so wise\"}").
//                when().
//                post().
//                then(). 
//                statusCode(200);
//    }
//
//    @Test
//    public void postCompany() {
//        given().
//                contentType(MediaType.APPLICATION_JSON).
//                body("{\"company\":\"this is so wise\"}").
//                when().
//                post().
//                then(). 
//                statusCode(200);
//    }
//
//    @Test
//    public void postHobby() {
//        given().
//                contentType(MediaType.APPLICATION_JSON).
//                body("{\"hibby\":\"this is so wise\"}").
//                when().
//                post().
//                then(). 
//                statusCode(200);
//    }
//
//    @Test
//    public void postPhone() {
//        given().
//                contentType(MediaType.APPLICATION_JSON).
//                body("{\"phone\":\"this is so wise\"}").
//                when().
//                post().
//                then(). 
//                statusCode(200);
//    }
//
//    //PUT TEST
//    @Test
//    public void putAddress() {
//        given().
////                contentType(MediaType.APPLICATION_JSON).
////                body("{\"address\":\"this is so wise\"}").
////                when().
////                put().
////                then(). 
////                statusCode(200);
////    }
////
////    @Test
////    public void putPerson() {
////        given().
////                contentType(MediaType.APPLICATION_JSON).
////                body("{\"person\":\"this is so wise\"}").
////                when().
////                put().
////                then(). 
////                statusCode(200);
////    }
////
////    @Test
////    public void putCompany() {
////        given().
////                contentType(MediaType.APPLICATION_JSON).
////                body("{\"company\":\"this is so wise\"}").
////                when().
////                put().
////                then(). 
////                statusCode(200);
////    }
////
////    @Test
////    public void putHobby() {
////        given().
////                contentType(MediaType.APPLICATION_JSON).
////                body("{\"hobby\":\"this is so wise\"}").
////                when().
////                put().
////                then(). 
////                statusCode(200);
////    }
////
////    @Test
////    public void putPhone() {
////        given().
////                contentType(MediaType.APPLICATION_JSON).
////                body("{\"phone\":\"this is so wise\"}").
////                when().
////                put().
////                then().
////                statusCode(200);   
////    }
//
    //DELETE TEST
//    @Test
//    public void deleteAddress() {
//        given().
//                when().
//                delete().
//                then(). 
//                statusCode(200);
//    }

//    @Test
//    public void deletePerson() {
//        given().
//                when().
//                delete("/delete/person/4").
//                then(). 
//                statusCode(200);
//    }
//
//    @Test
//    public void deleteCompany() {
//        given().
//                when().
//                delete("/delete/company/2").
//                then(). 
//                statusCode(200);
//    }
//
//    @Test
//    public void deleteHobby() {
//        given().
//                when().
//        delete().
//        then(). 
//                statusCode(200);
//    }
//
//    @Test
//    public void deletePhone() {
//        given().
//                when().
//        delete().
//        then(). 
//                statusCode(200);
//    }

}
    

