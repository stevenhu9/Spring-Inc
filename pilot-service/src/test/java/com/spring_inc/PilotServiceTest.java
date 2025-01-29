package com.spring_inc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spring_inc.dtos.PilotDTO;
import com.spring_inc.models.Pilot;
import com.spring_inc.repositories.PilotRepository;
import com.spring_inc.services.PilotService;

public class PilotServiceTest {

    @Mock
    private PilotRepository repo;

    @InjectMocks
    private PilotService pilotService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        Pilot pilot1 = new Pilot(1, "Captain", 5000, "XYZ123", "John Doe", "Boeing 747", 101, 201);
        Pilot pilot2 = new Pilot(2, "First Officer", 3000, "ABC456", "Jane Doe", "Airbus A320", 102, 202);
        List<Pilot> pilots = Arrays.asList(pilot1, pilot2);
        when(repo.findAll()).thenReturn(pilots);

        // Act
        ResponseEntity<Iterable<Pilot>> response = pilotService.findAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(((List<Pilot>) response.getBody()).containsAll(pilots));
    }

    @Test
    public void testFindById_WhenPilotExists() {
        
        Pilot pilot = new Pilot(1, "Captain", 5000, "XYZ123", "John Doe", "Boeing 747", 101, 201);
        when(repo.existsById(1)).thenReturn(true);
        when(repo.findById(1)).thenReturn(Optional.of(pilot));

        ResponseEntity<Pilot> response = pilotService.findById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(pilot, response.getBody());
    }

    @Test
    public void testFindById_WhenPilotDoesNotExist() {

        when(repo.existsById(1)).thenReturn(false);


        ResponseEntity<Pilot> response = pilotService.findById(1);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testAddOne() {

        PilotDTO pilotDTO = new PilotDTO(1, "Captain", 5000, "XYZ123", "John Doe", "Boeing 747", 101, 201);
        Pilot pilot = new Pilot(0, pilotDTO.getPilotRank(), pilotDTO.getFlightHoursLogged(), pilotDTO.getLicense(), pilotDTO.getFullName(), pilotDTO.getAircraft(), pilotDTO.getCommanderId(), pilotDTO.getSquadronId());
        when(repo.save(any(Pilot.class))).thenReturn(pilot);

        ResponseEntity<Pilot> response = pilotService.addOne(pilotDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(pilot.getFullName(), response.getBody().getFullName());
    }

    @Test
    public void testUpdateOne_WhenPilotExists() {

        PilotDTO pilotDTO = new PilotDTO(1, "Captain", 5000, "XYZ123", "John Doe", "Boeing 747", 101, 201);
        Pilot pilot = new Pilot(1, pilotDTO.getPilotRank(), pilotDTO.getFlightHoursLogged(), pilotDTO.getLicense(), pilotDTO.getFullName(), pilotDTO.getAircraft(), pilotDTO.getCommanderId(), pilotDTO.getSquadronId());
        when(repo.existsById(1)).thenReturn(true);
        when(repo.save(any(Pilot.class))).thenReturn(pilot);

        ResponseEntity<Pilot> response = pilotService.updateOne(1, pilotDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(pilot.getFullName(), response.getBody().getFullName());
    }

    @Test
    public void testUpdateOne_WhenPilotDoesNotExist() {

        PilotDTO pilotDTO = new PilotDTO(1, "Captain", 5000, "XYZ123", "John Doe", "Boeing 747", 101, 201);
        when(repo.existsById(1)).thenReturn(false);

        ResponseEntity<Pilot> response = pilotService.updateOne(1, pilotDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteOne() {

        doNothing().when(repo).deleteById(1);

        ResponseEntity<Void> response = pilotService.deleteOne(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(repo, times(1)).deleteById(1);
    }
}