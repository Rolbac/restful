package com.quique.demoRestful.services;

import com.quique.demoRestful.data.RegionDTO;
import com.quique.demoRestful.entities.Region;
import com.quique.demoRestful.exceptions.ErrorMessages;
import com.quique.demoRestful.exceptions.RegionNotValidException;
import com.quique.demoRestful.repositories.RegionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateRegionService implements Command<Region, RegionDTO>{

    private final RegionRepo regionRepo;

    public CreateRegionService(RegionRepo regionRepo) {
        this.regionRepo = regionRepo;
    }

    @Override
    public ResponseEntity<RegionDTO> execute(Region region) {
        Optional<Region> existingRegion = regionRepo.findById(region.getId());

        if (existingRegion.isPresent()) {
            throw new RegionNotValidException(ErrorMessages.REGION_ALREADY_EXISTS.getMessage());
        }

        Region regionSaved = regionRepo.save(region);
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegionDTO(regionSaved));
    }
}
