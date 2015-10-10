/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restAssured;

import tester.*;
import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.AdderFacade;
import facade.ServiceFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marcj_000
 */
public class Tester {
    
    public static void main(String[] args) {
        
 
   
        
        
             String pu = "CA2PU";
        
        Persistence.generateSchema(pu, null);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(pu);
        
        AdderFacade af = new AdderFacade(emf);
        ServiceFacade sf = new ServiceFacade(emf);
   

        //Citites
        CityInfo ci = new CityInfo(647, "New York");
        CityInfo ci2 = new CityInfo(5346, "New Dork");
        //Addresses
        Address address = new Address("Hello Kitty Strret1","Kurts address1");
        Address address2 = new Address("Adam Street","Polski Kurva Street");
        Address address3 = new Address("Company Street 3","HelloKittyCity");
        ci2.addAddress(address3);
        ci2.addAddress(address2);
        ci.addAddress(address);
        address3.setCityInfo(ci2);
        address.setCityInfo(ci);
        address2.setCityInfo(ci2);
        //Companies
        Company c = new Company("kurt company", "this company is so wonnegut", 123213, 30, Long.MIN_VALUE);
        Phone phone = new Phone(2452345, "company tel");
        c.setAddress(address3);
        phone.addInfoEntity(c);
        c.addPhone(phone);     
        //People
        Person kurt = new Person("Kurt","Wonnegut4");
        phone = new Phone(12345678,"kurts phone1");
        phone.addInfoEntity(kurt);
        kurt.addPhone(phone);
        kurt.setAddress(address);
        kurt.setEmail("kurt@wonnegut.com");
        
        Person adam = new Person("Adam","Lewandowski");
        Phone phonee = new Phone(92375678,"kurts phone1");
        phonee.addInfoEntity(adam);
        adam.addPhone(phonee);
        adam.setAddress(address2);
        adam.setEmail("adam@kurva.com");
        //Hobbies
        Hobby badminton = new Hobby("Badminton","Badminton");
        Hobby drugs = new Hobby("drugs", "drugs");
        kurt.addHobby(badminton);
        kurt.addHobby(drugs);
        adam.addHobby(drugs);
        
        af.addCityInfo(ci);
        af.addCityInfo(ci2);
        af.addCompany(c);
        af.addPerson(adam);
        af.addPerson(kurt);
        drugs.addPerson(kurt);
        drugs.addPerson(adam);
        badminton.addPerson(kurt);

    }
    
    public void runner(){
         
        String pu = "CA2PU";
        
        Persistence.generateSchema(pu, null);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(pu);
        
        AdderFacade af = new AdderFacade(emf);
        ServiceFacade sf = new ServiceFacade(emf);
        
        
        //Citites
        CityInfo ci = new CityInfo(647, "New York");
        CityInfo ci2 = new CityInfo(5346, "New Dork");
        //Addresses
        Address address = new Address("Hello Kitty Strret1","Kurts address1");
        Address address2 = new Address("Adam Street","Polski Kurva Street");
        Address address3 = new Address("Company Street 3","HelloKittyCity");
        ci2.addAddress(address3);
        ci2.addAddress(address2);
        ci.addAddress(address);
        address3.setCityInfo(ci2);
        address.setCityInfo(ci);
        address2.setCityInfo(ci2);
        //Companies
        Company c = new Company("kurt company", "this company is so wonnegut", 123213, 30, Long.MIN_VALUE);
        Phone phone = new Phone(2452345, "company tel");
        c.setAddress(address3);
        phone.addInfoEntity(c);
        c.addPhone(phone);     
        //People
        Person kurt = new Person("Kurt","Wonnegut4");
        phone = new Phone(12345678,"kurts phone1");
        phone.addInfoEntity(kurt);
        kurt.addPhone(phone);
        kurt.setAddress(address);
        kurt.setEmail("kurt@wonnegut.com");
        
        Person adam = new Person("Adam","Lewandowski");
        Phone phonee = new Phone(92375678,"kurts phone1");
        phonee.addInfoEntity(adam);
        adam.addPhone(phonee);
        adam.setAddress(address2);
        adam.setEmail("adam@kurva.com");
        //Hobbies
        Hobby badminton = new Hobby("Badminton","Badminton");
        Hobby drugs = new Hobby("drugs", "drugs");
        kurt.addHobby(badminton);
        kurt.addHobby(drugs);
        adam.addHobby(drugs);
        
        af.addCityInfo(ci);
        af.addCityInfo(ci2);
        af.addCompany(c);
        af.addPerson(adam);
        af.addPerson(kurt);
        drugs.addPerson(kurt);
        drugs.addPerson(adam);
        badminton.addPerson(kurt);
        
        

    }
    
}
