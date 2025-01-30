package com.spring_inc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

import com.spring_inc.api.SquadronClient;
import com.spring_inc.controllers.PilotController;
import com.spring_inc.dtos.PilotDTO;
import com.spring_inc.dtos.ResponseDTO;
import com.spring_inc.models.Pilot;
import com.spring_inc.services.PilotService;

@ExtendWith(MockitoExtension.class)
public class PilotControllerTest {

    @Mock
    private PilotService service;

    @Mock
    private SquadronClient squadronClient;

    @InjectMocks
    private PilotController controller;

    private Pilot pilot;
    private PilotDTO pilotDTO;

    @BeforeEach
    void setUp() {
        pilot = new Pilot(1, "Novice", 10, "LZIWSQ", "Jane Doe", "F-15", 1, 1);
        pilotDTO = new PilotDTO(1, "Novice", 10, "LZIWSQ", "Jane Doe", "F-15", 1, 1);
    }

    // Test cases for findAll()
    @Test
    void testFindAll_Success() {
        when(service.findAll()).thenReturn(ResponseEntity.ok(Arrays.asList(pilot)));
        ResponseEntity<Iterable<Pilot>> response = controller.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().iterator().hasNext());
    }

    @Test
    void testFindAll_EmptyList() {
        when(service.findAll()).thenReturn(ResponseEntity.ok(Collections.emptyList()));
        ResponseEntity<Iterable<Pilot>> response = controller.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().iterator().hasNext());
    }

    // Test cases for findById()
    @Test
    void testFindById_Success() {
        when(service.findById(1)).thenReturn(ResponseEntity.ok(pilot));
        ResponseEntity<Pilot> response = controller.findById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().getFullName());
    }

    @Test
    void testFindById_NotFound() {
        when(service.findById(1)).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        ResponseEntity<Pilot> response = controller.findById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for addOne()
    @Test
    void testAddOne_Success() {
        when(service.addOne(pilotDTO)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(pilot));
        ResponseEntity<Pilot> response = controller.addOne(pilotDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().getFullName());
    }

    @Test
    void testAddOne_Failure() {
        when(service.addOne(pilotDTO)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        ResponseEntity<Pilot> response = controller.addOne(pilotDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for updateOne()
    @Test
    void testUpdateOne_Success() {
        when(service.updateOne(1, pilotDTO)).thenReturn(ResponseEntity.ok(pilot));
        ResponseEntity<Pilot> response = controller.updateOne(1, pilotDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().getFullName());
    }

    @Test
    void testUpdateOne_NotFound() {
        when(service.updateOne(1, pilotDTO)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        ResponseEntity<Pilot> response = controller.updateOne(1, pilotDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for deleteOne()
    @Test
    void testDeleteOne_Success() {
        when(service.deleteOne(1)).thenReturn(ResponseEntity.ok().build());
        ResponseEntity<ResponseDTO> response = controller.deleteOne(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteOne_Failure() {
        when(service.deleteOne(1)).thenReturn(ResponseEntity.status(HttpStatus.CONFLICT).build());
        ResponseEntity<ResponseDTO> response = controller.deleteOne(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}