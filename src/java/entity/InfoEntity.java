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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author marcj_000
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED )
public class InfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    
    @OneToMany(mappedBy = "infoEntity")
    private List<Phone> phones = new ArrayList();
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;
    
    public void addPhone(Phone phone){
        phones.add(phone);
    }
    
    public void deletePhone(Phone phone){
        phones.remove(phone);
    }
    
    public InfoEntity(String email) {
        this.email = email;
    }

    public InfoEntity() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entity.InfoEntity[ id=" + id + " ]";
    }
    
}
