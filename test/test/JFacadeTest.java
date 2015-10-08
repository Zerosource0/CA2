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
import exception.PersonNotFoundException;
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
    public void deletePerson() throws PersonNotFoundException {

        df.deletePerson(1);
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
        df.deleteCompany(2);
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
    
    //SERVICE FACADE TEST
    
    public void addForServiceTests(){
         Address address = new Address("Hello Kitty Strret1","Kurts address1");
        CityInfo ci = new CityInfo(647, "New York");
        CityInfo ci2 = new CityInfo(5346, "New York");
        ci.addAddress(address);
        address.setCityInfo(ci);
        af.addCityInfo(ci);
        af.addCityInfo(ci2);
        
        Person kurt = new Person("Kurt","Wonnegut4");
        Phone phone = new Phone(12345678,"kurts phone1");
        phone.addInfoEntity(kurt);
        kurt.addPhone(phone);
        kurt.setAddress(address);
        
        Person adam = new Person("Adam","Lewandowski");
        Phone phonee = new Phone(92375678,"kurts phone1");
        phonee.addInfoEntity(adam);
        adam.addPhone(phonee);
        
        adam.setAddress(address);
        
        phone = new Phone(87654321,"kurts other phone");
        phone.addInfoEntity(kurt);
        kurt.addPhone(phone);
        
        Company c = new Company("kurt company", "this company is so wonnegut", 123213, 30, Long.MIN_VALUE);
        phone= new Phone(2452345, "company tel");
        phone.addInfoEntity(c);
        c.addPhone(phone);
        af.addCompany(c);
        c = new Company("adam company", "this company is so adam", 435, 20, Long.MIN_VALUE);
        af.addCompany(c);
        c = new Company("random company", "this company is so adam", 324324, 10, Long.MIN_VALUE);
        af.addCompany(c);
        
        Hobby badminton = new Hobby("Badminton","Badminton");
        Hobby drugs = new Hobby("drugs", "drugs");
        kurt.addHobby(badminton);
        kurt.addHobby(drugs);
        adam.addHobby(drugs);
        drugs.addPerson(kurt);
        drugs.addPerson(adam);
        badminton.addPerson(kurt);
        kurt.setEmail("kurt@wonnegut.com");
        
        af.addPerson(adam);
        af.addPerson(kurt);
    }
    
    @Test
    public void getPersonFromPhone(){
        
        addForServiceTests();
        Person pp1= sf.getPersonFromPhone(12345678);
        assertEquals("Kurt", pp1.getFirstName());
        assertEquals("Wonnegut", pp1.getLastName());
    }
    
    @Test
    public void getCompanyFromPhone(){
 
        Company c=sf.getCompanyFromPhone(2452345);
        assertEquals("kurt company", c.getName());
    }
    
    @Test
    public void getCompanyFromCvr(){
    
        Company c = sf.getCompanyFromCvr(123213);
        assertEquals("kurt company", c.getName());
    }
    
    @Test
    public void getPeopleFromHobby(){
        Hobby h = new Hobby("drug","drug");
        assertEquals(2 ,sf.getPeopleFromHobby(h).size());
    }
    
    @Test
    public void getPeopleFromCity(){
        
        assertEquals(2,sf.getPeopleFromCity("New York").size());
    }
    
    @Test
    public void getCountOfPeopleFromHobby(){
        Hobby h = new Hobby("badminton","badminton");
        assertEquals(2 ,sf.getPeopleFromHobby(h).size());
    }
    
    @Test
    public void getZip(){
    
        assertEquals(2,sf.getZipCodes().size());
    }
    
    @Test
    public void getCompanyBySize(){
    
        List<Company> CompanyList = sf.getCompanyWithMoreThanXEmployees(15);
        assertEquals("kurt company", CompanyList.get(0).getName());
    }

    @AfterClass
    public static void tearDownClass() {
    }

}
