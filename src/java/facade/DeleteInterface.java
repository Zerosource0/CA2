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
    
    public void deletePerson(Person person);
    
    public void deleteCompany(Company company);
    
    public void deleteHobby(Hobby hobby);
    
    public void deleteCityInfo(CityInfo cityInfo);
    
    public void deletePhone(Phone phone);
    
    public void deleteAddress(Address address);
    
}
