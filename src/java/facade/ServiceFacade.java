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
import javax.persistence.Query;

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
   // @Override
    @Override
    public Person getPersonFromPhone(Integer phone) 
    {
        EntityManager em = getEntityManager();
        Query q = em.createQuery(
        "SELECT p FROM Person p, Phone ph WHERE PH.infoEntity = p and ph.number =:phone");
       /* Query q = em.createNativeQuery("select person.* "
                + "from phone, person "
                + "where phone.INFOENTITY_id=person.id and phone.number="+ phone);
        */
               q.setParameter("phone", phone);
        //em.close();
        return (Person) q.getSingleResult();
    }

    @Override
    public Company getCompanyFromPhone(Integer phone) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery(
        "SELECT c FROM Company c, Phone ph WHERE PH.infoEntity = c and ph.number =:phone");
        q.setParameter("phone", phone);
        //em.close();
        return (Company) q.getSingleResult();
    }

    @Override
    public Company getCompanyFromCvr(Integer cvr) {
         EntityManager em = getEntityManager();
        Query q = em.createQuery("select c from Company c where c.cvr=:cvr");
        q.setParameter("cvr", cvr);
        return (Company)q.getSingleResult();
    }

    @Override
    public List<Person> getPeopleFromHobby(Hobby hobby) 
    {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("select p from Person p, Hobby h where p.hobbies = h and h = :hobby");
        q.setParameter("hobby", hobby);
        List<Person> lp = q.getResultList();
        return lp ;
    }

    @Override
    public List<Person> getPeopleFromCity(String city) 
    {
        EntityManager em = getEntityManager();
        Query q = em.createQuery(
        "select p from Person p, Address a, CityInfo ci WHERE a.infoEntities=p and a.cityInfo=ci and ci.city=:city ");
        q.setParameter("city", city);
        return (List<Person>) q.getResultList();
    }

    @Override
    public Integer getCountOfPeopleFromHobby(Hobby hobby) 
    {
        return getPeopleFromHobby(hobby).size();
    }

    @Override
    public List<Integer> getZipCodes() 
    {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("select ci.zip from CityInfo ci");
        return (List<Integer>) q.getResultList();
    }

    @Override
    public List<Company> getCompanyWithMoreThanXEmployees(Integer numberEmployees) 
    {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("select c from Company c where c.numEmployees>:numberEmployees ");
        q.setParameter("numberEmployees", numberEmployees);
        return (List<Company>) q.getResultList();
    }

}
