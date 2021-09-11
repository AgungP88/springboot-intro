/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.crud.service;

import com.metrodatambkm.crud.model.Country;
import com.metrodatambkm.crud.model.Region;
import com.metrodatambkm.crud.repository.CountryRepository;
import com.metrodatambkm.crud.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author hp
 */
@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository, RegionRepository regionRepository) {
        this.countryRepository = countryRepository;
    }

    public Country saveAsianCountry(Country country) {
        //country-nya udah ada atau belum?
        Country oldCountry = countryRepository.findByName(country.getName());
        if (oldCountry != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Sudah ada");
        }
        //kita tambahin si regionnya biar region_id = 1
        country.setRegion(new Region(1L)); //oke masukin ID 1 ya
        return countryRepository.save(country);
    }

    public Country saveEuropeanCountry(Country country) {
        //country-nya udah ada atau belum?
        Country oldCountry = countryRepository.findByName(country.getName());
        if (oldCountry != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Sudah ada");
        }
        //kita tambahin si regionnya biar region_id = 1
        country.setRegion(new Region("Europe")); // oke bikin kolom baru dengan isi Europe
        return countryRepository.save(country);
    }
}
