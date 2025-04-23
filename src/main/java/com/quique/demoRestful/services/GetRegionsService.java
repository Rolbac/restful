package com.quique.demoRestful.services;

import com.quique.demoRestful.data.RegionDTO;
import com.quique.demoRestful.entities.Region;
import com.quique.demoRestful.repositories.RegionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRegionsService implements Query<Void, List<RegionDTO>>{

    private final RegionRepo regionRepo;

    public GetRegionsService(RegionRepo regionRepo) {
        this.regionRepo = regionRepo;
    }

    @Override
    public ResponseEntity<List<RegionDTO>> execute(Void input) {
        List<Region> regions = regionRepo.findAll();
        List<RegionDTO> regionsDTOs = regions.stream().map(RegionDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(regionsDTOs);
    }
}
