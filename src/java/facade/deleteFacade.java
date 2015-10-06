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
    public Person deletePerson(Person person) {
        EntityManager em = emf.createEntityManager();
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
    public Company deleteCompany(Company company) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(company);
            em.getTransaction().commit();
            return company;
        } finally {
            em.close();
        }    }

    @Override
    public Hobby deleteHobby(Hobby hobby) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(hobby);
            em.getTransaction().commit();
            return hobby;
        } finally {
            em.close();
        }    }

    @Override
    public CityInfo deleteCityInfo(CityInfo cityInfo) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(cityInfo);
            em.getTransaction().commit();
            return cityInfo;
        } finally {
            em.close();
        }    }

    @Override
    public Phone deletePhone(Phone phone) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(phone);
            em.getTransaction().commit();
            return phone;
        } finally {
            em.close();
        }    }

    @Override
    public Address deleteAddress(Address address) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(address);
            em.getTransaction().commit();
            return address;
        } finally {
            em.close();
        }    }

}
