/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restAssured;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.defaultParser;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.parsing.Parser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author williambech
 */
public class JDeleteRestAssured {
    
    public JDeleteRestAssured() {
    }
    
        @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseURI = "http://localhost:8080/CA2";
        defaultParser = Parser.JSON;
        basePath = "/api";
    }

    @Test
    public void deletePerson() {
        given().
                when().
                delete("/person/delete/2").
                then(). 
                statusCode(200);
    }

    @Test
    public void deleteCompany() {
        given().
                when().
                delete("/company/delete/1").
                then(). 
                statusCode(200);
    }

    
}
