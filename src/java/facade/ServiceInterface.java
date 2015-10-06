/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Company;
import entity.Person;
import entity.Phone;
import java.util.List;

/**
 *
 * @author marcj_000
 */
public interface ServiceInterface {
    
    public Person getPersonFromPhone(Integer phone);
            
    public Company getCompanyFromPhone(Integer phone);
    
    public Company getCompanyFromCvr(Integer cvr);
    
    public List<Person> getPeopleFromHobby(String hobby);
    
    public List<Person> getPeopleFromCity(String city);
    
    public Integer getCountOfPeopleFromHobby(String hobby);
    
    public List<Integer> getZipCodes();
    
    public List<Company> getCompanyWithMoreThanXEmployees(Integer numberEmployees);
    
}
