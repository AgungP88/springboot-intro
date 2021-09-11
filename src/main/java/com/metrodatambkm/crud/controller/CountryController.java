/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.crud.controller;

import com.metrodatambkm.crud.model.Country;
import com.metrodatambkm.crud.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("country")
public class CountryController {
    @Autowired
    CountryService service;
    
    @PostMapping("asia")
    public Country saveAsianCountry(@RequestBody Country country){
        return service.saveAsianCountry(country);
    }
    @PostMapping("europe")
    public Country saveEuropeCountry(@RequestBody Country country){
        return service.saveEuropeanCountry(country);
    }
}