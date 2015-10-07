/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

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
        EntityManager em = emf.createEntityManager();
        
        AdderFacade af = new AdderFacade(emf);
        ServiceFacade sf = new ServiceFacade(emf);
        
        Address address = new Address("Hello Kitty Strret1","Kurts address1");
        CityInfo ci = new CityInfo(647, "New York");
        CityInfo ci2 = new CityInfo(5346, "New York");
        ci.addAddress(address);
        address.setCityInfo(ci);
        af.addCityInfo(ci);
        af.addCityInfo(ci2);
        
        Person p = new Person("Kurt","Wonnegut4");
        Phone phone = new Phone(12345678,"kurts phone1");
        phone.addInfoEntity(p);
        p.addPhone(phone);
        p.setAddress(address);
        
        Person pp = new Person("Adam","Lewandowski");
        Phone phonee = new Phone(92375678,"kurts phone1");
        phonee.addInfoEntity(pp);
        pp.addPhone(phonee);
        pp.addHobby(new Hobby("drugs", "drugs"));
        pp.setAddress(address);
        af.addPerson(pp);
        
        phone = new Phone(87654321,"kurts other phone");
        phone.addInfoEntity(p);
        p.addPhone(phone);
        
        Company c = new Company("kurt company", "this company is so wonnegut", 123213, 30, Long.MIN_VALUE);
        phone= new Phone(2452345, "company tel");
        phone.addInfoEntity(c);
        c.addPhone(phone);
        af.addCompany(c);
        c = new Company("adam company", "this company is so adam", 435, 20, Long.MIN_VALUE);
        af.addCompany(c);
        c = new Company("random company", "this company is so adam", 324324, 10, Long.MIN_VALUE);
        af.addCompany(c);
        //List<CityInfo> cities = sf.getCityInfoList();
       // System.out.println("length of cities> "+cities.size());
       // address.setCityInfo(cities.get(1));
        
        
        p.addHobby(new Hobby("Badminton","Badminton"));
        p.addHobby(new Hobby("drugs", "drugs"));
        
        
        p.setEmail("kurt@wonnegut.com");
        
        af.addPerson(p);
        pp= sf.getPersonFromPhone(12345678);
        System.out.println(pp.toString());
        c=sf.getCompanyFromPhone(2452345);
        System.out.println(c.toString());
        System.out.println(sf.getCompanyFromCvr(123213).toString());
        List<Person> l = sf.getPeopleFromHobby(new Hobby("drugs", "drugs"));
        System.out.println(l.size());
        for (Person l1 : l) 
        {
            System.out.println("HOOBIES: "+l1.toString());
        }
        l=sf.getPeopleFromCity("New York");
        System.out.println(l.size());
        for (Person l1 : l) 
        {
            System.out.println("CITIES: "+l1.toString());
        }
        List<Integer> zips = sf.getZipCodes();
        for (Integer zip : zips) {
            System.out.println("THIS IS ZIP "+zip);
        }
        List<Company> companies = sf.getCompanyWithMoreThanXEmployees(15);
        for (Company company : companies) {
            System.out.println("COMPANIESS "+company.toString());
        }
        
        
    }
    
}
