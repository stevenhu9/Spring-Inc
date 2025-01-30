package com.spring_inc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;
import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spring_inc.dtos.CommanderDTO;
import com.spring_inc.dtos.PilotDTO;
import com.spring_inc.dtos.ResponseDTO;
import com.spring_inc.dtos.SquadronDTO;
import com.spring_inc.models.Squadron;
import com.spring_inc.repositories.SquadronRepository;
import com.spring_inc.services.SquadronService;

@ExtendWith(MockitoExtension.class)
public class SquadronServiceTest {

    @Mock
    private SquadronRepository repo;

    @InjectMocks
    private SquadronService squadronService;

    private Squadron squadron;
    private SquadronDTO squadronDTO;
    private CommanderDTO commanderDTO;
    private PilotDTO pilotDTO;
    byte activeDuty = 1;
    
    Timestamp timestamp = Timestamp.from(Instant.now());

    @BeforeEach
    void setUp() {
        squadron = new Squadron(1, "Fire Squad", "Austin, TX", timestamp, "Classified", 0, "Active", 1);
        squadronDTO = new SquadronDTO("Alpha", "Base1", timestamp, "Mission1", 10, "Active", 1);
        commanderDTO = new CommanderDTO(1, "Commander1", "Rank1", 5, "Specialization1", activeDuty);
        pilotDTO = new PilotDTO(1, "Novice", 3, "IASLDS", "Jane Doe", "F-15", 1, 2);
    }
    
    // Test cases for findAll()
    @Test
    void testFindAll() {
        when(repo.findAll()).thenReturn(Arrays.asList(squadron));
        ResponseEntity<Iterable<Squadron>> response = squadronService.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().iterator().hasNext());
    }
    
    // Test cases for findById()
    @Test
    void testFindById() {
        when(repo.existsById(1)).thenReturn(true);
        when(repo.findById(1)).thenReturn(Optional.of(squadron));
        ResponseEntity<Squadron> response = squadronService.findById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Fire Squad", response.getBody().getSquadronName());
    }
    
    @Test
    void testFindByIdNotFound() {
        when(repo.existsById(1)).thenReturn(false);
        ResponseEntity<Squadron> response = squadronService.findById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for addOne()
    @Test
    void testAddOne() {
        when(repo.save(any(Squadron.class))).thenReturn(squadron);
        ResponseEntity<Squadron> response = squadronService.addOne(squadronDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Fire Squad", response.getBody().getSquadronName());
    }

    // Test cases for updateOne()
    @Test
    void testUpdateOne() {
        when(repo.existsById(1)).thenReturn(true);
        when(repo.save(any(Squadron.class))).thenReturn(squadron);
        ResponseEntity<Squadron> response = squadronService.updateOne(1, squadronDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Fire Squad", response.getBody().getSquadronName());
    }

    @Test
    void testUpdateOneNotFound() {
        when(repo.existsById(1)).thenReturn(false);
        ResponseEntity<Squadron> response = squadronService.updateOne(1, squadronDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for deleteOne()
    @Test
    void testDeleteOne() {
        doNothing().when(repo).deleteById(1);
        ResponseEntity<ResponseDTO> response = squadronService.deleteOne(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
    }

    // Test cases for deleteOneWithReplacement()
    @Test
    void testDeleteOneWithReplacement() {
        doNothing().when(repo).deleteById(1);
        ResponseEntity<ResponseDTO> response = squadronService.deleteOne(1, 2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
    }

    @Test
    void testDeleteOneWithReplacementError() {
        doThrow(new RuntimeException()).when(repo).deleteById(1);
        ResponseEntity<ResponseDTO> response = squadronService.deleteOne(1, 2);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
    }

    // Test cases for getCommander()
    @Test
    void testGetCommander() {
        when(repo.existsById(1)).thenReturn(true);
        when(repo.getCommanderID(1)).thenReturn(1);
        when(repo.getCommander(1)).thenReturn(commanderDTO);
        ResponseEntity<CommanderDTO> response = squadronService.getCommander(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Commander1", response.getBody().getCommanderName());
    }

    @Test
    void testGetCommanderNotFound() {
        when(repo.existsById(1)).thenReturn(false);
        ResponseEntity<CommanderDTO> response = squadronService.getCommander(1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for getPilot()
    @Test
    void testGetPilot() {
        when(repo.existsById(1)).thenReturn(true);
        when(repo.getPilot(1)).thenReturn(Arrays.asList(pilotDTO));
        ResponseEntity<Iterable<PilotDTO>> response = squadronService.getPilot(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().iterator().hasNext());
    }

    @Test
    void testGetPilotNotFound() {
        when(repo.existsById(1)).thenReturn(false);
        ResponseEntity<Iterable<PilotDTO>> response = squadronService.getPilot(1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for addPilot()
    @Test
    void testAddPilot() {
        when(repo.checkCapacity(1)).thenReturn(5);
        when(repo.addPilot(1, 1)).thenReturn(1);
        ResponseEntity<ResponseDTO> response = squadronService.addPilot(1, 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
    }

    @Test
    void testAddPilotFullSquad() {
        when(repo.checkCapacity(1)).thenReturn(10);
        ResponseEntity<ResponseDTO> response = squadronService.addPilot(1, 1);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
    }
}