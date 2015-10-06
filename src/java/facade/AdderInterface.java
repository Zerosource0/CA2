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
public interface AdderInterface {
    
    public void addPerson(Person person);
    
    public void addCompany(Company company);
    
    public void addHobby(Hobby hobby);
    
    public void addCityInfo(CityInfo cityInfo);
    
    public void addPhone(Phone phone);
    
    public void addAddress(Address address);
}
