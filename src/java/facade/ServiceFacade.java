/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marcj_000
 */
public class ServiceFacade implements ServiceInterface {

    private EntityManagerFactory emf;

    public ServiceFacade(EntityManagerFactory e) {
        emf = e;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
       public Person getPersonById(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Person.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPeople() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("select p from Person p").getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Company> getCompanies() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("select c from Company c").getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Hobby> getHobbies() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("select h from Hobby h").getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<CityInfo> getCityInfoList() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("select c from CityInfo c").getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Address> getAddresses() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("select a from Address a").getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Phone> getPhones() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("select p from Phone p").getResultList();
        } finally {
            em.close();
        }
    }

    //----------------------------------------------------------------------------------------------------------------------------------//
    @Override
    public Person getPersonFromPhone(Integer phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Company getCompanyFromPhone(Integer phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Company getCompanyFromCvr(Integer cvr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> getPeopleFromHobby(String hobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> getPeopleFromCity(String city) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getCountOfPeopleFromHobby(String hobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> getZipCodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Company> getCompanyWithMoreThanXEmployees(Integer numberEmployees) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
