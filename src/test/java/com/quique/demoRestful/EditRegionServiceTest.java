package com.quique.demoRestful;

import com.quique.demoRestful.data.RegionDTO;
import com.quique.demoRestful.data.UpdateRegionCommand;
import com.quique.demoRestful.entities.Region;
import com.quique.demoRestful.exceptions.RegionNotFoundException;
import com.quique.demoRestful.repositories.RegionRepo;
import com.quique.demoRestful.services.EditRegionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EditRegionServiceTest {

    @Mock//Dependency we need to run the test
    private RegionRepo regionRepo;

    @InjectMocks //what we test
    private EditRegionService editRegionService;

    @BeforeEach //what we need before running our test
    public void setup(){
        //initializes the repository and the service
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_valid_region_when_edit_region_service_then_return_updated_region(){
        Region region = new Region();
        region.setId(1);
        region.setName("Hulu");
        Region updatedRegion = new Region();
        region.setId(1);
        region.setName("Hulaaa");

        when(regionRepo.findById(1)).thenReturn(Optional.of(region));
        when(regionRepo.save(updatedRegion)).thenReturn(updatedRegion);

        UpdateRegionCommand command = new UpdateRegionCommand(1, updatedRegion);
        ResponseEntity<RegionDTO> response = editRegionService.execute(command);

        assertEquals(ResponseEntity.ok(new RegionDTO(updatedRegion)),response);

        verify(regionRepo, times(1)).findById(1);
        verify(regionRepo, times(1)).save(updatedRegion);
    }

    @Test
    public void given_region_does_not_exist_when_edit_region_service_then_throw_region_not_found_exception(){
        Region updatedRegion = new Region();
        updatedRegion.setId(1);
        updatedRegion.setName("Updated Name");

        when(regionRepo.findById(1)).thenReturn(Optional.empty());

        UpdateRegionCommand command = new UpdateRegionCommand(1, updatedRegion);

        assertThrows(RegionNotFoundException.class, () -> editRegionService.execute(command));
        verify(regionRepo, times(1)).findById(1);
    }
}
