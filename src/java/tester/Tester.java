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
        
        AdderFacade af = new AdderFacade(emf);
        ServiceFacade sf = new ServiceFacade(emf);
        /*
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
        
        Hobby badminton = new Hobby("Badminton","Badminton");
        Hobby drugs = new Hobby("drugs", "drugs");
        p.addHobby(badminton);
        p.addHobby(drugs);
        pp.addHobby(drugs);
        drugs.addPerson(p);
        drugs.addPerson(pp);
        badminton.addPerson(p);
        
        
        p.setEmail("kurt@wonnegut.com");
        
        af.addPerson(p);
        
        */
        Person pp= sf.getPersonFromPhone(12345678);
        System.out.println(pp.toString());
        Company c=sf.getCompanyFromPhone(2452345);
        System.out.println(c.toString());
        System.out.println(sf.getCompanyFromCvr(123213).toString());
        
        int hobbyDrugIndex = 0;
        List<Hobby> hobbyList = sf.getHobbies();
        for (Hobby hobby : hobbyList) {
            if (hobby.getName().equals("drugs")) hobbyDrugIndex = hobby.getId()-1;
        }
        
        List<Person> peopleFromHobby = sf.getPeopleFromHobby(sf.getHobbies().get(hobbyDrugIndex));
        System.out.println("people from hobby drug size: "+peopleFromHobby.size());
        
        for (Person personFromHobby : peopleFromHobby) 
        {
            System.out.println("HOBBIES: "+personFromHobby.toString());
        }
        List<Person> peopleFromCity =sf.getPeopleFromCity("New York");
        System.out.println("people from City size: "+peopleFromCity.size());
        
        for (Person personFromcity : peopleFromCity) 
        {
            System.out.println("CITIES: "+personFromcity.toString());
        }
        List<Integer> zips = sf.getZipCodes();
            System.out.println("Number of ZipCodes: " + zips.size());
        
        List<Company> companies = sf.getCompanyWithMoreThanXEmployees(15);
        for (Company company : companies) {
            System.out.println("COMPANIESS "+company.toString());
        }
        
        
    }
    
}
