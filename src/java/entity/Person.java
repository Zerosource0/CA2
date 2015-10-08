/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author marcj_000
 */
@Entity
@Table(name = "person")
public class Person extends InfoEntity implements Serializable {

    @Column(name = "firstname")
    private String firstName;
    
    @Column(name = "lastname")
    private String lastName;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    private List<Hobby> hobbies = new ArrayList();

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
    
    public void addHobby(Hobby hobby){
        hobbies.add(hobby);
    }
    
    public void deleteHobby(Hobby hobby){
        hobbies.remove(hobby);
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", hobbies=" + hobbies + '}';
    }
    
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
}
