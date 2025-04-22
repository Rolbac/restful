package com.quique.demoRestful.api.controllers;

import com.quique.demoRestful.entities.Region;
import com.quique.demoRestful.repositories.RegionRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    final private RegionRepo repository;

    public RegionController(RegionRepo repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Region> findAll(){
        return repository.findAll();
    }
}
