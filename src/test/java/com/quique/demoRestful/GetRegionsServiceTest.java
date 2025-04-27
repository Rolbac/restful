package com.quique.demoRestful;

import com.quique.demoRestful.data.RegionDTO;
import com.quique.demoRestful.entities.Region;
import com.quique.demoRestful.repositories.RegionRepo;
import com.quique.demoRestful.services.GetRegionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetRegionsServiceTest {

    @Mock//Dependency we need to run the test
    private RegionRepo regionRepo;

    @InjectMocks //what we test
    private GetRegionsService getRegionsService;

    private List<Region> regions;

    @BeforeEach //what we need before running our test
    public void setup(){
        //initializes the repository and the service
        MockitoAnnotations.openMocks(this);

        regions = new ArrayList<>();
        regions.add(createRegion(1,"Europe"));
        regions.add(createRegion(2,"Africa"));

    }

    @Test
    public void given_regions_exist_when_get_region_service_is_called_then_return_region_dtos(){
        //Given
        when(regionRepo.findAll()).thenReturn(regions);

        //When
        ResponseEntity<List<RegionDTO>> response = getRegionsService.execute(null);

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<RegionDTO> regionsDTOs = response.getBody();
        assertNotNull(regionsDTOs);
        assertFalse(regionsDTOs.isEmpty());

        assertEquals(2, regionsDTOs.size());
        assertEquals("Europe",regionsDTOs.get(0).getName());
        assertEquals("Africa",regionsDTOs.get(1).getName());

        verify(regionRepo, times(1)).findAll();
    }

    private Region createRegion(Integer id, String name){
        Region region = new Region();
        region.setId(id);
        region.setName(name);
        return region;
    }
}
