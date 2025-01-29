package com.Spring_inc;

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

import com.Spring_inc.api.SquadronClient;
import com.Spring_inc.controllers.CommanderController;
import com.Spring_inc.dtos.CommanderDTO;
import com.Spring_inc.dtos.ResponseDTO;
import com.Spring_inc.models.Commander;
import com.Spring_inc.services.CommanderService;

@ExtendWith(MockitoExtension.class)
public class CommanderControllerTest {

    @Mock
    private CommanderService service;

    @Mock
    private SquadronClient client;

    @InjectMocks
    private CommanderController controller;

    private Commander commander;
    private CommanderDTO commanderDTO;
    private ResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        commander = new Commander(1, "Jane Doe", "General", 10, "Infantry", "Active");
        commanderDTO = new CommanderDTO("John Doe", "Novice", "Infantry", 5, "Active");
        responseDTO = new ResponseDTO("Success", true);
    }

    // Test cases for findAll()
    @Test
    void testFindAll_Success() {
        when(service.findAll()).thenReturn(ResponseEntity.ok(Arrays.asList(commander)));
        ResponseEntity<Iterable<Commander>> response = controller.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().iterator().hasNext());
    }

    @Test
    void testFindAll_EmptyList() {
        when(service.findAll()).thenReturn(ResponseEntity.ok(Collections.emptyList()));
        ResponseEntity<Iterable<Commander>> response = controller.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().iterator().hasNext());
    }

    // Test cases for findById()
    @Test
    void testFindById_Success() {
        when(service.findById(1)).thenReturn(ResponseEntity.ok(commander));
        ResponseEntity<Commander> response = controller.findById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().getCommanderName());
    }

    @Test
    void testFindById_NotFound() {
        when(service.findById(1)).thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        ResponseEntity<Commander> response = controller.findById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for addOne()
    @Test
    void testAddOne_Success() {
        when(service.addOne(commanderDTO)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(commander));
        ResponseEntity<Commander> response = controller.addOne(commanderDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().getCommanderName());
    }

    @Test
    void testAddOne_Failure() {
        when(service.addOne(commanderDTO)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        ResponseEntity<Commander> response = controller.addOne(commanderDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Test cases for updateOne()
    @Test
    void testUpdateOne_Success() {
        when(service.updateOne(1, commanderDTO)).thenReturn(ResponseEntity.ok(commander));
        ResponseEntity<Commander> response = controller.updateOne(1, commanderDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().getCommanderName());
    }

    @Test
    void testUpdateOne_NotFound() {
        when(service.updateOne(1, commanderDTO)).thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        ResponseEntity<Commander> response = controller.updateOne(1, commanderDTO);
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
}
