/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import facade.AdderFacade;
import facade.DeleteFacade;
import facade.ServiceFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcj_000
 */
public class facadeTest {
    
    public facadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        String pu = "CA2PU";
        
        Persistence.generateSchema(pu, null);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(pu);
        
        AdderFacade adderFacade = new AdderFacade(emf);
        DeleteFacade deleteFacade = new DeleteFacade(emf);
        ServiceFacade serviceFacade = new ServiceFacade(emf);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
