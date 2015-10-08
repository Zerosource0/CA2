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
import exception.PersonNotFoundException;

/**
 *
 * @author marcj_000
 */
public interface DeleteInterface {
    
    public Person deletePerson(int id) throws PersonNotFoundException;
    
    public Company deleteCompany(int id);
    
    public Hobby deleteHobby(int id);
    
    public CityInfo deleteCityInfo(int id);
    
    public Phone deletePhone(int id);
    
    public Address deleteAddress(int id);
    
}
