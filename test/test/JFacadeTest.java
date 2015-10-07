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
import facade.AdderFacade;
import facade.DeleteFacade;
import facade.ServiceFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcj_000
 */
public class JFacadeTest {

    public JFacadeTest() {

    }

    @BeforeClass
    public static void setUpClass() {

        String pu = "CA2PU";
        Persistence.generateSchema(pu, null);
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
        assertEquals(1, sf.getPeople().size());
    }

    @Test
    public void deletePerson() {

        df.deletePerson(1);
        assertEquals(0, sf.getPeople().size());
    }

    //ADDRESS TEST
    Address a = new Address("water", "boy");

    @Test
    public void addAddress() {

        af.addAddress(a);
        assertEquals(1, sf.getAddresses().size());
    }

    @Test
    public void deleteAddress() {

        df.deleteAddress(1);
        assertEquals(0, sf.getAddresses().size());
    }

    //CITY INFO TEST
    CityInfo c = new CityInfo(1234, "boy");

    @Test
    public void addCityInfo() {

        af.addCityInfo(c);
        assertEquals(1353, sf.getCityInfoList().size());
    }

    @Test
    public void deleteCityInfo() {

        df.deleteCityInfo(1353);
        assertEquals(1352, sf.getCityInfoList().size());
    }

    //COMPANY TEST
    Company p = new Company("comp", "something", 124, 24, (long) 3);

    @Test
    public void addCompany() {

        af.addCompany(p);
        assertEquals(1, sf.getCompanies().size());
    }

    @Test
    public void deleteCompany() {
        df.deleteCompany(2);
        assertEquals(0, sf.getCompanies().size());
    }

    //HOBBY TEST
    Hobby h = new Hobby("water", "boying");

    @Test
    public void addHobby() {

        af.addHobby(h);
        assertEquals(1, sf.getHobbies().size());
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

    @AfterClass
    public static void tearDownClass() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
