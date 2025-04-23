package com.quique.demoRestful.services;

import com.quique.demoRestful.data.RegionDTO;
import com.quique.demoRestful.data.UpdateRegionCommand;
import com.quique.demoRestful.entities.Region;
import com.quique.demoRestful.repositories.RegionRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditRegionService implements Command<UpdateRegionCommand, RegionDTO>{

    private final RegionRepo regionRepo;

    public EditRegionService(RegionRepo regionRepo) {
        this.regionRepo = regionRepo;
    }

    @Override
    public ResponseEntity<RegionDTO> execute(UpdateRegionCommand command) {
        Optional<Region> regionOptional = regionRepo.findById(command.getId());
        if(regionOptional.isPresent()){
            Region region = command.getRegion();
            region.setId(command.getId());
            regionRepo.save(region);
            return ResponseEntity.ok(new RegionDTO(region));
        }

        return null;
    }
}
