package com.quique.demoRestful.services;

import com.quique.demoRestful.data.RegionDTO;
import com.quique.demoRestful.entities.Region;
import com.quique.demoRestful.exceptions.RegionNotFoundException;
import com.quique.demoRestful.repositories.RegionRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetRegionService implements Query<Integer, RegionDTO>{

    private final RegionRepo regionRepo;

    public GetRegionService(RegionRepo regionRepo) {
        this.regionRepo = regionRepo;
    }

    @Override
    public ResponseEntity<RegionDTO> execute(Integer input) {
        Optional<Region> regionOptional = regionRepo.findById(input);
        if(regionOptional.isPresent()){
            return ResponseEntity.ok(new RegionDTO(regionOptional.get()));
        }

        throw new RegionNotFoundException();
    }
}
