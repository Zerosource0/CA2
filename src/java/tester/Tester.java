/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

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
    }
    
}
