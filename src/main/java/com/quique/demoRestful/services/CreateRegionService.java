package com.quique.demoRestful.services;

import com.quique.demoRestful.data.RegionDTO;
import com.quique.demoRestful.entities.Region;
import com.quique.demoRestful.repositories.RegionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateRegionService implements Command<Region, RegionDTO>{

    private final RegionRepo regionRepo;

    public CreateRegionService(RegionRepo regionRepo) {
        this.regionRepo = regionRepo;
    }

    @Override
    public ResponseEntity<RegionDTO> execute(Region region) {
        Region regionSaved = regionRepo.save(region);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegionDTO(regionSaved));
    }
}
