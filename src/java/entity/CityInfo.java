/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marcj_000
 */
@Entity
@Table(name = "cityInfo")
public class CityInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "id")
    private Integer id;
        
    @Column(name = "city")
    private String city;
    
    @Column(name = "zip")
    private Integer zip;


    @OneToMany(mappedBy = "cityInfo")
    private List<Address> addresses = new ArrayList();
    
    public void addAddress(Address address){
        addresses.add(address);
    }
    
    public void deleteAddress(Address address){
        addresses.remove(address);
    }
    
    public CityInfo(Integer zipCode, String city) {
        this.zip = zipCode;
        this.city = city;
    }

    public CityInfo() {
    }

    public Integer getZipCode() {
        return zip;
    }

    public void setZipCode(Integer zipCode) {
        this.zip = zipCode;
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
