/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;
import org.junit.*;
import tester.Tester;


/**
 *
 * @author marcj_000
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JFacadeTest {

    public JFacadeTest() {
        

    }

    @BeforeClass
    public static void setUpClass() {
        
        String pu = "CA2PU";
        Persistence.generateSchema("CA2PU", null);
    }
    String pu = "CA2PU";
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(pu);

    AdderFacade af = new AdderFacade(emf);
    DeleteFacade df = new DeleteFacade(emf);
    ServiceFacade sf = new ServiceFacade(emf);

    //PERSON TEST   
    Person pe = new Person("water", "boy");

    @Test
    public void addPerson() {

        af.addPerson(pe);
        Person p = sf.getPeople().get(0);
        assertEquals("water", p.getFirstName());
    }

    @Test
    public void deletePerson() throws EntityNotFoundException {

        df.deletePerson(2);
        assertEquals(0, sf.getPeople().size());
    }

    //ADDRESS TEST
    Address a = new Address("water", "boy");

    @Test
    public void addAddress() {

        af.addAddress(a);
        Address a1 = sf.getAddresses().get(0);
        assertEquals("water", a1.getStreet());
    }

    @Test
    public void deleteAddress() {

        df.deleteAddress(1);
        assertEquals(0, sf.getAddresses().size());
    }

    //CITY INFO TEST
    CityInfo c = new CityInfo(1, "boy");

    @Test
    public void addCityInfo() {

        af.addCityInfo(c);
        CityInfo ci = sf.getCityInfoList().get(0);
        assertEquals("boy", ci.getCity());
    }

    @Test
    public void deleteCityInfo() {

        df.deleteCityInfo(1);
        assertEquals(0, sf.getCityInfoList().size());
    }

    //COMPANY TEST
    Company p = new Company("comp", "something", 124, 24, (long) 3);

    @Test
    public void addCompany() {

        af.addCompany(p);
        Company c = sf.getCompanies().get(0);
        assertEquals("comp", c.getName());
    }

    @Test
    public void deleteCompany() {
        df.deleteCompany(1);
        assertEquals(0, sf.getCompanies().size());
    }

    //HOBBY TEST
    Hobby h = new Hobby("water", "boying");

    @Test
    public void addHobby() {

        af.addHobby(h);
        Hobby h1 = sf.getHobbies().get(0);
        assertEquals("water", h1.getName());
    }
    @Test
    public void deleteHobby() {

        df.deleteHobby(1);
        assertEquals(0, sf.getHobbies().size());
    }

     //PHONE TEST
    Phone ph = new Phone(1234, "Water Boy Phone");

    @Test
    public void addPhone() {

        af.addPhone(ph);
        assertEquals(1, sf.getPhones().size());
    }
    @Test
    public void deletePhone() {

        df.deletePhone(1);
        assertEquals(0, sf.getPhones().size());
    }
}
