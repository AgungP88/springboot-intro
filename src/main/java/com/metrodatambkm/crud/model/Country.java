/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.crud.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import jdk.nashorn.internal.objects.annotations.Getter;

/**
 *
 * @author hp
 */

@Entity
@Table(name = "tb_country")
public class Country {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long id;
    
    @Column(name = "country_name", length = 25)
    private String name;    

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
    
    public Country() {
    }
    
    public Country(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Country(String name, Region region) {
        this.name = name;
        this.region = region;
    }
    
    
}
