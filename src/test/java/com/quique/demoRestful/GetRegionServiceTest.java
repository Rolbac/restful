package com.quique.demoRestful;

import com.quique.demoRestful.data.RegionDTO;
import com.quique.demoRestful.entities.Region;
import com.quique.demoRestful.exceptions.RegionNotFoundException;
import com.quique.demoRestful.repositories.RegionRepo;
import com.quique.demoRestful.services.GetRegionService;
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

public class GetRegionServiceTest {

    @Mock//Dependency we need to run the test
    private RegionRepo regionRepo;

    @InjectMocks //what we test
    private GetRegionService getRegionService;

    @BeforeEach //what we need before running our test
    public void setup(){
        //initializes the repository and the service
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_region_exist_when_get_region_service_return_region_dto(){
        Region region = new Region();
        region.setId(1);
        region.setName("Hulu");

        when(regionRepo.findById(1)).thenReturn(Optional.of(region));

        ResponseEntity<RegionDTO> response = getRegionService.execute(1);

        assertEquals(ResponseEntity.ok(new RegionDTO(region)),response);

        verify(regionRepo, times(1)).findById(1);
    }

    @Test
    public void given_region_does_not_exist_when_get_region_service_throw_region_not_found_exception(){
        when(regionRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(RegionNotFoundException.class, () -> getRegionService.execute(1));
        verify(regionRepo, times(1)).findById(1);
    }
}
