package com.quique.demoRestful.services;

import com.quique.demoRestful.entities.Region;
import com.quique.demoRestful.exceptions.RegionNotFoundException;
import com.quique.demoRestful.repositories.RegionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteRegionService implements Command<Integer, Void>{

    private final RegionRepo regionRepo;

    public DeleteRegionService(RegionRepo regionRepo) {
        this.regionRepo = regionRepo;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Region> regionOptional = regionRepo.findById(id);
        if(regionOptional.isPresent()){
            regionRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new RegionNotFoundException();
    }
}
