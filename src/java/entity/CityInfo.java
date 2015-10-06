/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author marcj_000
 */
@Entity
public class CityInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer zipCode;
    private String city;

    @OneToMany(mappedBy = "cityInfo")
    private List<Address> addresses = new ArrayList();
    
    public void addAddress(Address address){
        addresses.add(address);
    }
    
    public void deleteAddress(Address address){
        addresses.remove(address);
    }
    
    public CityInfo(Integer zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public CityInfo() {
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entity.CityInfo[ id=" + id + " ]";
    }
    
}
