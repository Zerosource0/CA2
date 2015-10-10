/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import com.jayway.restassured.parsing.Parser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author williambech
 */
public class JPostRestAssured {
    
    public JPostRestAssured() {
    }
    
        @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseURI = "http://localhost:8080/CA2";
        defaultParser = Parser.JSON;
        basePath = "/api";
    }
    
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
}
