package com.Spring_inc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Spring_inc.dtos.CommanderDTO;
import com.Spring_inc.dtos.ResponseDTO;
import com.Spring_inc.models.Commander;
import com.Spring_inc.repositories.CommanderRepository;
import com.Spring_inc.services.CommanderService;

@ExtendWith(MockitoExtension.class)
public class CommanderServiceTest {

    @Mock
    private CommanderRepository repo;

    @InjectMocks
    private CommanderService commanderService;

    private Commander commander;
    private CommanderDTO commanderDTO;

    @BeforeEach
    void setUp() {
        commander = new Commander(1, "Jane Doe", "General", 10, "Infantry", "Active");
        commanderDTO = new CommanderDTO("John Doe", "Low", "Infantry", 1, "Active");
    }

    @Test
    void testFindAll() {
        when(repo.findAll()).thenReturn(Arrays.asList(commander));
        ResponseEntity<Iterable<Commander>> response = commanderService.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().iterator().hasNext());
    }

    @Test
    void testFindById() {
        when(repo.existsById(1)).thenReturn(true);
        when(repo.findById(1)).thenReturn(Optional.of(commander));
        ResponseEntity<Commander> response = commanderService.findById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().getCommanderName());
    }

    @Test
    void testFindByIdNotFound() {
        when(repo.existsById(1)).thenReturn(false);
        ResponseEntity<Commander> response = commanderService.findById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testAddOne() {
        when(repo.save(any(Commander.class))).thenReturn(commander);
        ResponseEntity<Commander> response = commanderService.addOne(commanderDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().getCommanderName());
    }

    @Test
    void testUpdateOne() {
        when(repo.existsById(1)).thenReturn(true);
        when(repo.save(any(Commander.class))).thenReturn(commander);
        ResponseEntity<Commander> response = commanderService.updateOne(1, commanderDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Jane Doe", response.getBody().getCommanderName());
    }

    @Test
    void testUpdateOneNotFound() {
        when(repo.existsById(1)).thenReturn(false);
        ResponseEntity<Commander> response = commanderService.updateOne(1, commanderDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteOne() {
        doNothing().when(repo).deleteById(1);
        ResponseEntity<ResponseDTO> response = commanderService.deleteOne(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
    }

    @Test
    void testDeleteOneWithException() {
        doThrow(new RuntimeException()).when(repo).deleteById(1);
        ResponseEntity<ResponseDTO> response = commanderService.deleteOne(1);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
    }

    @Test
    void testDeleteOneWithReplacement() {
        doNothing().when(repo).reassignSquad(2, 1);
        doNothing().when(repo).reassignPilot(2, 1);
        doNothing().when(repo).deleteById(1);
        ResponseEntity<ResponseDTO> response = commanderService.deleteOne(1, 2);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
    }

    @Test
    void testDeleteOneWithReplacementError() {
        doThrow(new RuntimeException()).when(repo).reassignSquad(2, 1);
        ResponseEntity<ResponseDTO> response = commanderService.deleteOne(1, 2);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
    }
}