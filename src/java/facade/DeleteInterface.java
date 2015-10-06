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

/**
 *
 * @author marcj_000
 */
public interface DeleteInterface {
    
    public Person deletePerson(Person person);
    
    public Company deleteCompany(Company company);
    
    public Hobby deleteHobby(Hobby hobby);
    
    public CityInfo deleteCityInfo(CityInfo cityInfo);
    
    public Phone deletePhone(Phone phone);
    
    public Address deleteAddress(Address address);
    
}
