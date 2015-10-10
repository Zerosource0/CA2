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
import javax.ws.rs.core.MediaType;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author williambech
 */
public class JPutRestAssured {
    
    public JPutRestAssured() {
    }
    
        @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        baseURI = "http://localhost:8080/CA2";
        defaultParser = Parser.JSON;
        basePath = "/api";
    }

    @Test
    public void editPerson() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                body("{" + 
    "\"firstName\" "+": "+"\""+ "dudeNewer" +"\", "+
    "\"lastName\" "+": "+"\""+"ImNewest" +"\", "+
    "\"email\" "+": "+"\""+"nestew@new.new" +"\", "+
    "\"city\" "+": "+"\""+"newestCity" +"\", "+
    "\"zipCode\" "+": "+"\""+ 123 +"\", "+
    "\"street\" "+": "+"\""+"newestStreet" +"\", "+
    "\"additionalInfo\" "+": "+"\""+"emh?" +"\", "+
    "\"phone\" "+": "+"\""+1234 +"\", "+
    "\"description\" "+": "+"\""+"new phone" +"\""+
    "}").
                when().
                put("/person/1").
                then(). 
                statusCode(200);
    }

    @Test
    public void editCompany() {
        given().
                contentType(MediaType.APPLICATION_JSON).
                body("{" + 
    "\"name\" "+": "+"\""+ "newestCompany" +"\", "+
    "\"cdescription\" "+": "+"\""+"newest comp" +"\", "+
    "\"cvr\" "+": "+"\""+ 123 +"\", "+
    "\"numEmployees\" "+": "+"\""+500 +"\", "+
    "\"marketValue\" "+": "+"\""+123 +"\", "+
    "\"email\" "+": "+"\""+"nfdsest@comp.fds" +"\", "+
    "\"city\" "+": "+"\""+ "newest dork" +"\", "+
    "\"zipCode\" "+": "+"\""+123 +"\", "+
    "\"street\" "+": "+"\""+"dorathonest" +"\", "+
    "\"additionalInfo\" "+": "+"\""+"dudeest tiz be a company" +"\", "+
    "\"phone\" "+": "+"\""+123 +"\", "+
    "\"description\" "+": "+"\""+ "a phoneest? much wow." +"\""+
    "}").
                when().
                put("/company/1").
                then(). 
                statusCode(200);
    }
    
}
