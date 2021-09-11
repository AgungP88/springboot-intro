/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.crud.service;

import com.metrodatambkm.crud.model.Country;
import com.metrodatambkm.crud.model.Region;
import com.metrodatambkm.crud.repository.RegionRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author hp
 */
@Service
public class RegionService {
    private RegionRepository regionRepository;
    
    @Autowired
    public RegionService(RegionRepository regionRepository){
        this.regionRepository = regionRepository;
    }
    
    public List<Region> findAll(){
        return regionRepository.findAll();
    }
    
    public Region findById(Long id){
        try {
            return regionRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Optional<Region> getById(Long id){
            return regionRepository.findById(id);
    }
    
//    public Region findById(Long id){
//        
//         return regionRepository.findById(id).
//                 orElseThrow(()-> new ResponseStatusException(
//                         HttpStatus.NOT_FOUND,"Maaf data tidak ditemukan"));
//    }
    
    public Region saveRegion(Region region){
        return regionRepository.save(region);
    }
    
//    public Region saveRegion(Region region){
//        if (region.getId() != null) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data already exists");
//        }
//        return regionRepository.save(region);
//    }
    
    public Region update(Long id,Region region){
            return regionRepository.save(region);
    }
    
//    public Region update(Long id,Region region){
//        if (regionRepository.existsById(id)) {
//            Region oldRegion = regionRepository.getById(id);
//            oldRegion.setName(region.getName());
//            return regionRepository.save(oldRegion);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data Found");
//    }
   
    
    public void delete(Region region){
        regionRepository.delete(region);
    }

    public void deleteById(Long id) {
        regionRepository.deleteById(id);
    }
    
    public boolean checkIfPresent(Long id){
        return getById(id).isPresent();
    }
    
    public Region saveRegionAndCountry() {
        Region newRegion = new Region(2L, "Europe");
        
        Set<Country> countries = new HashSet<Country>();
        countries.add(new Country("Spain",newRegion));
        countries.add(new Country("Luxemborg",newRegion));
        countries.add(new Country("Italy",newRegion));
        
        newRegion.setCountries(countries);
        return regionRepository.save(newRegion);
    }
}
