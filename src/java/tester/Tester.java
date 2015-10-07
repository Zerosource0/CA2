/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import entity.Address;
import entity.CityInfo;
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
        EntityManager em = emf.createEntityManager();
        
        AdderFacade af = new AdderFacade(emf);
        ServiceFacade sf = new ServiceFacade(emf);
        
        Person p = new Person("Kurt","Wonnegut4");
        Phone phone = new Phone(12345678,"kurts phone1");
        phone.addInfoEntity(p);
        p.addPhone(phone);
        
        phone = new Phone(87654321,"kurts other phone");
        phone.addInfoEntity(p);
        p.addPhone(phone);
        
        Address address = new Address("Hello Kitty Strret1","Kurts address1");
        List<CityInfo> cities = sf.getCityInfoList();
        address.setCityInfo(cities.get(1));
        
        p.setAddress(address);
        p.addHobby(new Hobby("Badminton","Badminton"));
        
        p.setEmail("kurt@wonnegut.com");
        
        af.addPerson(p);
        
    }
    
}
