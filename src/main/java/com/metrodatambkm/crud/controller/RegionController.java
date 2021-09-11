/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.crud.controller;

import com.metrodatambkm.crud.model.Region;
import com.metrodatambkm.crud.service.RegionService;
import io.swagger.annotations.ApiResponse;
import java.util.List;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import static springfox.documentation.spring.web.readers.operation.ResponseMessagesReader.message;

/**
 *
 * @author hp
 */
@Controller
@RestController
public class RegionController {
    
    private RegionService service;
    private int statusCode;
    
    @Autowired 
    public RegionController(RegionService service){
        this.service = service;
    }
    
    
    @GetMapping("")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    
    @GetMapping("/region")
    public ResponseEntity<Object> getAll(){
        try{
            List<Region> region = service.findAll();
            for (Region reg : region) {
                System.out.println(reg.getId() + " - "+ reg.getName());
            }
            return ResponseHandler.generateResponse(
                    "Successfully retrieved data!", HttpStatus.OK, region);
        }catch(Exception e){
            return ResponseHandler.generateResponse(
                    "Data Not Found", HttpStatus.NOT_FOUND, null);
        } 
    }
    
    @GetMapping("/region/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        //model.addAttribute("region", r);
        Region r = new Region();
        r = service.findById(id);
        if(r.getId() != null){
            System.out.println(r.getId()+" "+r.getName());
            return ResponseHandler.generateResponse(
                    "Successfully retrieved data!", HttpStatus.OK, r);
        } else {
            return ResponseHandler.generateResponse(
                    "Data Not Found", HttpStatus.NOT_FOUND, null);
        }
    }
    
    @PutMapping("/region/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, 
            @RequestBody Region region){
        if (service.getById(region.getId()).isPresent()) {
            service.update(id, region);
            return ResponseHandler.generateResponse(
                    "Successfully update data!", HttpStatus.CREATED, region);
        }else{
            return ResponseHandler.generateResponse(
                    "Data not found", HttpStatus.NOT_FOUND, null);
        }
    }
    
    @DeleteMapping("/region/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        if (service.getById(id).isPresent()) {
            service.deleteById(id);
            return ResponseHandler.generateResponse(
                    "Success delete data", HttpStatus.OK, null);
        }else{
            return ResponseHandler.generateResponse(
                    "data not found", HttpStatus.NOT_FOUND, null);
        }
    }
    
    @PostMapping("/region")
    public ResponseEntity<Object> save(@RequestBody Region region){
        if (service.checkIfPresent(region.getId())) {
            return ResponseHandler.generateResponse(
                    "Data already exists", HttpStatus.CONFLICT, null);
        }else{
            service.saveRegion(region);
            return ResponseHandler.generateResponse(
                    "Successfully added data!", HttpStatus.CREATED, region);
        }
    }
    
    @PostMapping("/region/europe")
    public Region createEurope(){
        return service.saveRegionAndCountry();
    }
}
