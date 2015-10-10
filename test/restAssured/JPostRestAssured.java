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
import entity.Person;
import entity.Phone;
import javax.ws.rs.core.MediaType;
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

    @Test
    public void postPerson() {
        given().
                contentType(MediaType.APPLICATION_JSON).

                body("{" + 
    "\"firstName\" "+": "+"\""+ "newGuy" +"\", "+
    "\"lastName\" "+": "+"\""+"lastNew" +"\", "+
    "\"email\" "+": "+"\""+"new@new.new" +"\", "+
    "\"city\" "+": "+"\""+"newCity" +"\", "+
    "\"zipCode\" "+": "+"\""+ 123 +"\", "+
    "\"street\" "+": "+"\""+"newStreet" +"\", "+
    "\"additionalInfo\" "+": "+"\""+"emh?" +"\", "+
    "\"phone\" "+": "+"\""+1234 +"\", "+
    "\"description\" "+": "+"\""+"new phone" +"\""+
    "}").
                when().
                post("/person").
                then(). 
                statusCode(200);
    }

    @Test
    public void postCompany() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                body("{" + 
    "\"name\" "+": "+"\""+ "newCompany" +"\", "+
    "\"cdescription\" "+": "+"\""+"new comp" +"\", "+
    "\"cvr\" "+": "+"\""+ 123 +"\", "+
    "\"numEmployees\" "+": "+"\""+500 +"\", "+
    "\"marketValue\" "+": "+"\""+123 +"\", "+
    "\"email\" "+": "+"\""+"nfds@comp.fds" +"\", "+
    "\"city\" "+": "+"\""+ "new dork" +"\", "+
    "\"zipCode\" "+": "+"\""+123 +"\", "+
    "\"street\" "+": "+"\""+"dorathon" +"\", "+
    "\"additionalInfo\" "+": "+"\""+"dude tiz be a company" +"\", "+
    "\"phone\" "+": "+"\""+123 +"\", "+
    "\"description\" "+": "+"\""+ "a phone? much wow." +"\""+
    "}").
                when().
                post("/company").
                then(). 
                statusCode(200);
    }

}
