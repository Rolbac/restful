package com.quique.demoRestful;

import com.quique.demoRestful.entities.Region;
import com.quique.demoRestful.exceptions.RegionNotFoundException;
import com.quique.demoRestful.repositories.RegionRepo;
import com.quique.demoRestful.services.DeleteRegionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteRegionServiceTest {

    @Mock//Dependency we need to run the test
    private RegionRepo regionRepo;

    @InjectMocks //what we test
    private DeleteRegionService deleteRegionService;

    @BeforeEach //what we need before running our test
    public void setup(){
        //initializes the repository and the service
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void when_deleting_existing_region_then_return_response_entity_with_appropriate_status_code(){
        //Given
        Region regionToDelete = new Region();
        regionToDelete.setId(1);
        regionToDelete.setName("Europe");

        when(regionRepo.findById(1)).thenReturn(Optional.of(regionToDelete));
        doNothing().when(regionRepo).deleteById(1);

        //When
        ResponseEntity<Void> response = deleteRegionService.execute(1);

        // Then



        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());

        verify(regionRepo, times(1)).findById(1);
        verify(regionRepo, times(1)).deleteById(1);
    }

    @Test
    public void when_deleting_non_existing_region_then_throw_region_not_found_exception(){
         int nonExistingRegionId = 999;

        when(regionRepo.findById(nonExistingRegionId)).thenReturn(Optional.empty());

        assertThrows(RegionNotFoundException.class, () -> deleteRegionService.execute(nonExistingRegionId));

        verify(regionRepo, times(1)).findById(nonExistingRegionId);
        verify(regionRepo, times(0)).deleteById(any());
    }
}
