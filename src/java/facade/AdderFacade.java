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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author marcj_000
 */
public class AdderFacade implements AdderInterface {

    private EntityManagerFactory emf;

    public AdderFacade(EntityManagerFactory e) {
        emf = e;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person addPerson(Person person) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return person;
        } finally {
            em.close();
        }
    }

    @Override
    public Company addCompany(Company company) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();
            return company;
        } finally {
            em.close();
        }
    }

    @Override
    public Hobby addHobby(Hobby hobby) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
            return hobby;
        } finally {
            em.close();
        }
    }

    @Override
    public CityInfo addCityInfo(CityInfo cityInfo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cityInfo);
            em.getTransaction().commit();
            return cityInfo;
        } finally {
            em.close();
        }
    }

    @Override
    public Phone addPhone(Phone phone) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
            return phone;
        } finally {
            em.close();
        }
    }

    @Override
    public Address addAddress(Address address) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
            return address;
        } finally {
            em.close();
        }
    }

}
