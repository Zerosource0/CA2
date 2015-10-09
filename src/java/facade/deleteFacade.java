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
import exception.EntityNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author williambech
 */
public class DeleteFacade implements DeleteInterface {

    private EntityManagerFactory emf;

    public DeleteFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person deletePerson(int id) throws EntityNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null) {
            throw new EntityNotFoundException("No Person found with provided id");
        }
        //Remove hobbies
        List<Hobby> hobbies = person.getHobbies();
        for (Hobby hobby : hobbies) {
            person.removeHobby(hobby);
        }
        //remove address;
        person.getAddress().removeInfoEntity(person);
        person.setAddress(null);
        //Phones should be removed automatically
        
        try {
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            return person;
        } finally {
            em.close();
        }
    }

    @Override
    public Company deleteCompany(int id) {
        EntityManager em = emf.createEntityManager();
        Company company = em.find(Company.class, id);
        company.getAddress().removeInfoEntity(company);
        company.setAddress(null);
        
        
        try {
            em.getTransaction().begin();
            em.remove(company);
            em.getTransaction().commit();
            return company;
        } finally {
            em.close();
        }
    }

    @Override
    public Hobby deleteHobby(int id) {
        EntityManager em = emf.createEntityManager();
        Hobby hobby = em.find(Hobby.class, id);
        try {
            em.getTransaction().begin();
            em.remove(hobby);
            em.getTransaction().commit();
            return hobby;
        } finally {
            em.close();
        }
    }

    @Override
    public CityInfo deleteCityInfo(int id) {
        EntityManager em = emf.createEntityManager();
        CityInfo cityInfo = em.find(CityInfo.class, id);
        try {
            em.getTransaction().begin();
            em.remove(cityInfo);
            em.getTransaction().commit();
            return cityInfo;
        } finally {
            em.close();
        }
    }

    @Override
    public Phone deletePhone(int id) {
        EntityManager em = emf.createEntityManager();
        Phone phone = em.find(Phone.class, id);
        try {
            em.getTransaction().begin();
            em.remove(phone);
            em.getTransaction().commit();
            return phone;
        } finally {
            em.close();
        }
    }

    @Override
    public Address deleteAddress(int id) {
        EntityManager em = emf.createEntityManager();
        Address address = em.find(Address.class, id);
        try {
            em.getTransaction().begin();
            em.remove(address);
            em.getTransaction().commit();
            return address;
        } finally {
            em.close();
        }
    }

}
