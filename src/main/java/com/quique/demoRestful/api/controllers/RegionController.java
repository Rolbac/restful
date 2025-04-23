package com.quique.demoRestful.api.controllers;

import com.quique.demoRestful.data.RegionDTO;
import com.quique.demoRestful.data.UpdateRegionCommand;
import com.quique.demoRestful.entities.Region;
import com.quique.demoRestful.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RegionController {

    final private CreateRegionService createRegionService;
    final private GetRegionsService getRegionsService;
    final private EditRegionService editRegionService;
    final private DeleteRegionService deleteRegionService;
    final private GetRegionService getRegionService;

    public RegionController(CreateRegionService createRegionService,
                            GetRegionsService getRegionsService,
                            EditRegionService editRegionService,
                            DeleteRegionService deleteRegionService,
                            GetRegionService getRegionService) {

        this.createRegionService = createRegionService;
        this.getRegionsService = getRegionsService;
        this.editRegionService = editRegionService;
        this.deleteRegionService = deleteRegionService;
        this.getRegionService = getRegionService;
    }

//    @GetMapping
//    public List<Region> findAll(){
//        return repository.findAll();
//    }

    @PostMapping("/api/region")
    public ResponseEntity<RegionDTO> createRegion(@RequestBody Region region){
        return createRegionService.execute(region);
    }

    @GetMapping("/api/regions")
    public ResponseEntity<List<RegionDTO>> getAllRegion(){
        return getRegionsService.execute(null);
    }

    @GetMapping("/api/region/{id}")
    public ResponseEntity<RegionDTO> getRegionById(@PathVariable Integer id){
        return getRegionService.execute(id);
    }

    @PutMapping("/api/region/{id}")
    public ResponseEntity<RegionDTO> editRegion(@PathVariable Integer id, @RequestBody Region region){
        return editRegionService.execute(new UpdateRegionCommand(id, region));
    }

    @DeleteMapping("/api/region/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Integer id){
        return deleteRegionService.execute(id);
    }
}
