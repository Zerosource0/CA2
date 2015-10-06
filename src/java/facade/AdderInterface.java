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
    
    public Person addPerson(Person person);
    
    public Company addCompany(Company company);
    
    public Hobby addHobby(Hobby hobby);
    
    public CityInfo addCityInfo(CityInfo cityInfo);
    
    public Phone addPhone(Phone phone);
    
    public Address addAddress(Address address);
}
