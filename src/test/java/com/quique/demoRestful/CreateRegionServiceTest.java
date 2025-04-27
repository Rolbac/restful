package com.quique.demoRestful;

import com.quique.demoRestful.data.RegionDTO;
import com.quique.demoRestful.entities.Region;
import com.quique.demoRestful.exceptions.RegionNotValidException;
import com.quique.demoRestful.repositories.RegionRepo;
import com.quique.demoRestful.services.CreateRegionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateRegionServiceTest {

    @Mock//Dependency we need to run the test
    private RegionRepo regionRepo;

    @InjectMocks //what we test
    private CreateRegionService createRegionService;

    @BeforeEach //what we need before running our test
    public void setup(){
        //initializes the repository and the service
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void when_creating_new_region_then_return_regionDTO_without_id(){
        //Given
        Region regionToSave = new Region();
        regionToSave.setName("Europe");
        regionToSave.setId(1);

        when(regionRepo.findById(regionToSave.getId())).thenReturn(Optional.empty());
        when(regionRepo.save(any(Region.class))).thenReturn(regionToSave);

        //When
        ResponseEntity<RegionDTO> response = createRegionService.execute(regionToSave);

        // Then
        RegionDTO returnedRegionDTO = response.getBody();

        assertNotNull(returnedRegionDTO);
        assertEquals("Europe", returnedRegionDTO.getName());

        verify(regionRepo, times(1)).findById(regionToSave.getId());
        verify(regionRepo, times(1)).save(regionToSave);
    }

    @Test
    public void when_creating_region_with_existing_id_then_throw_region_not_valid_exception(){
        Region regionToSave = new Region();
        regionToSave.setName("Europe");
        regionToSave.setId(1);

        when(regionRepo.findById(1)).thenReturn(Optional.of(regionToSave));

        assertThrows(RegionNotValidException.class, () -> createRegionService.execute(regionToSave));

        verify(regionRepo, times(1)).findById(1);
        verify(regionRepo, times(0)).save(any(Region.class));
    }
}
