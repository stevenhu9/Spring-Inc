package com.spring_inc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spring_inc.api.CommanderClient;
import com.spring_inc.controllers.SquadronController;
import com.spring_inc.dtos.CommanderDTO;
import com.spring_inc.dtos.PilotDTO;
import com.spring_inc.dtos.ResponseDTO;
import com.spring_inc.dtos.SquadronDTO;
import com.spring_inc.models.Squadron;
import com.spring_inc.services.SquadronService;

@ExtendWith(MockitoExtension.class)
public class SquadronControllerTest {

    @Mock
    private SquadronService service;

    @Mock
    private CommanderClient client;

    @InjectMocks
    private SquadronController controller;

    private Squadron squadron;
    private SquadronDTO squadronDTO;
    private CommanderDTO commanderDTO;
    private PilotDTO pilotDTO;
    private ResponseDTO responseDTO;
    
    Timestamp timestamp = Timestamp.from(Instant.now());
    
    byte activeDuty = 1;
    
    @BeforeEach
    void setUp() {
        squadron = new Squadron(1, "Fire Squad", "Austin, TX", timestamp, "Classified", 0, "Active", 1);
        squadronDTO = new SquadronDTO("Alpha", "Base1", timestamp, "Mission1", 10, "Active", 1);
        commanderDTO = new CommanderDTO(1, "Jane Doe", "Commander", 5, "Infantry", activeDuty);
        pilotDTO = new PilotDTO(1, "Novice", 3, "ISDAL", "John Doe", "F-15", 2, 3);
        responseDTO = new ResponseDTO("Success", true);
    }

    // Test cases for findAll()
    @Test
    void testFindAll_Success() {
        when(service.findAll()).thenReturn(ResponseEntity.ok(Arrays.asList(squadron)));
        ResponseEntity<Iterable<Squadron>> response = controller.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().iterator().hasNext());
    }

    @Test
    void testFindAll_EmptyList() {
        when(service.findAll()).thenReturn(ResponseEntity.ok(Collections.emptyList()));
        ResponseEntity<Iterable<Squadron>> response = controller.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().iterator().hasNext());
    }

    // Test cases for findById()
    @Test
    void testFindById_Success() {
        when(service.findById(1)).thenReturn(ResponseEntity.ok(squadron));
        ResponseEntity<Squadron> response = controller.findById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Fire Squad", response.getBody().getSquadronName());
    }

    @Test
    void testFindById_NotFound() {
        when(service.findById(1)).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        ResponseEntity<Squadron> response = controller.findById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for addOne()
    @Test
    void testAddOne_Success() {
        when(service.addOne(squadronDTO)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(squadron));
        ResponseEntity<Squadron> response = controller.addOne(squadronDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Fire Squad", response.getBody().getSquadronName());
    }

    @Test
    void testAddOne_Failure() {
        when(service.addOne(squadronDTO)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        ResponseEntity<Squadron> response = controller.addOne(squadronDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for updateOne()
    @Test
    void testUpdateOne_Success() {
        when(service.updateOne(1, squadronDTO)).thenReturn(ResponseEntity.ok(squadron));
        ResponseEntity<Squadron> response = controller.updateOne(1, squadronDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Fire Squad", response.getBody().getSquadronName());
    }

    @Test
    void testUpdateOne_NotFound() {
        when(service.updateOne(1, squadronDTO)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        ResponseEntity<Squadron> response = controller.updateOne(1, squadronDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for deleteOne() without replacement
    @Test
    void testDeleteOne_Success() {
        when(service.deleteOne(1)).thenReturn(ResponseEntity.ok(responseDTO));
        ResponseEntity<ResponseDTO> response = controller.deleteOne(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
    }

    @Test
    void testDeleteOne_Failure() {
        when(service.deleteOne(1)).thenReturn(ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDTO("Error", false)));
        ResponseEntity<ResponseDTO> response = controller.deleteOne(1);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
    }

    // Test cases for deleteOne() with replacement
    @Test
    void testDeleteOneWithReplacement_Success() {
        when(service.deleteOne(1, 2)).thenReturn(ResponseEntity.ok(responseDTO));
        ResponseEntity<ResponseDTO> response = controller.deleteOne(1, 2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
    }

    @Test
    void testDeleteOneWithReplacement_Failure() {
        when(service.deleteOne(1, 2)).thenReturn(ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDTO("Error", false)));
        ResponseEntity<ResponseDTO> response = controller.deleteOne(1, 2);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
    }

    // Test cases for getCommander()
    @Test
    void testGetCommander_Success() {
        when(service.getCommander(1)).thenReturn(ResponseEntity.ok(commanderDTO));
        ResponseEntity<CommanderDTO> response = controller.getCommander(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().getCommanderName());
    }

    @Test
    void testGetCommander_NotFound() {
        when(service.getCommander(1)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        ResponseEntity<CommanderDTO> response = controller.getCommander(1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for getPilot()
    @Test
    void testGetPilot_Success() {
        when(service.getPilot(1)).thenReturn(ResponseEntity.ok(Arrays.asList(pilotDTO)));
        ResponseEntity<Iterable<PilotDTO>> response = controller.getPilot(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().iterator().hasNext());
    }

    @Test
    void testGetPilot_NotFound() {
        when(service.getPilot(1)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        ResponseEntity<Iterable<PilotDTO>> response = controller.getPilot(1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for addPilot()
    @Test
    void testAddPilot_Success() {
        when(service.addPilot(1, 1)).thenReturn(ResponseEntity.ok(responseDTO));
        ResponseEntity<ResponseDTO> response = controller.addPilot(1, 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
    }

    @Test
    void testAddPilot_Failure_SquadFull() {
        when(service.addPilot(1, 1)).thenReturn(ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseDTO("Squad is full", false)));
        ResponseEntity<ResponseDTO> response = controller.addPilot(1, 1);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
    }
}