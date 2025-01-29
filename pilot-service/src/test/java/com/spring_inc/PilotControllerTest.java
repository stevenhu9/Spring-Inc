package com.spring_inc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.spring_inc.api.SquadronClient;
import com.spring_inc.controllers.PilotController;
import com.spring_inc.dtos.PilotDTO;
import com.spring_inc.models.Pilot;
import com.spring_inc.services.PilotService;

import jakarta.persistence.EntityNotFoundException;


@AutoConfigureMockMvc
@WebMvcTest(PilotController.class)
public class PilotControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @MockitoBean
    private PilotService pilotService;
    
    @MockitoBean
    private SquadronClient squadronClient;

    @InjectMocks
    private PilotController pilotController;

    Timestamp timestamp = Timestamp.from(Instant.now());

    Pilot PILOT_1 = new Pilot(1, "Novice", 10, "LZIWSQ", "Jane Doe", "F-15", 1, 1);
    Pilot PILOT_2 = new Pilot(2, "Expert", 1200, "KSXIAA", "John Doe", "F-16", 2, 2);


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pilotController).build();
    }
    
    // Successful Attempts for Each CRUD Operation
    @Test
    public void getAllPilots_success() throws Exception {
        List<Pilot> pilots = Arrays.asList(PILOT_1, PILOT_2);

        when(pilotService.findAll()).thenReturn(ResponseEntity.ok(pilots));

        mockMvc.perform(get("/pilot")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("Jane Doe"))
                .andExpect(jsonPath("$[1].fullName").value("John Doe"));

        verify(pilotService, times(1)).findAll();
    }

    @Test
    public void getPilotById_success() throws Exception {
        when(pilotService.findById(1)).thenReturn(ResponseEntity.ok(PILOT_1));

        mockMvc.perform(get("/pilot/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Jane Doe"));

        verify(pilotService, times(1)).findById(1);
    }

    @Test
    public void addPilot_success() throws Exception {
        PilotDTO pilotDTO = new PilotDTO(2, "Expert", 1200, "KSXIAA", "John Doe", "F-16", 2, 2);
        when(pilotService.addOne(any(PilotDTO.class))).thenReturn(ResponseEntity.ok(PILOT_2));

        String content = objectWriter.writeValueAsString(pilotDTO);

        mockMvc.perform(post("/pilot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("John Doe"));

        verify(pilotService, times(1)).addOne(any(PilotDTO.class));
    }

    @Test
    public void updatePilot_success() throws Exception {
        PilotDTO pilotDTO = new PilotDTO(2, "Expert", 1200, "KSXIAA", "John Doe", "F-16", 2, 2);
        PILOT_2.setFullName("Jenifer Doe");

        when(pilotService.updateOne(eq(2), any(PilotDTO.class))).thenReturn(ResponseEntity.ok(PILOT_2));

        String content = objectWriter.writeValueAsString(pilotDTO);

        mockMvc.perform(put("/pilot/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Jenifer Doe"));

        verify(pilotService, times(1)).updateOne(eq(2), any(PilotDTO.class));
    }

    @Test
    public void deletePilot_success() throws Exception {
    	int pilotId = 1;

        when(pilotService.deleteOne(pilotId))
               .thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(delete("/pilot/1", pilotId)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNoContent());

        verify(pilotService, Mockito.times(1)).deleteOne(pilotId);
    }
    
    //Failure Messages For Each CRUD Operation
    //Delete One
    @Test
    public void deleteOne_PilotNotFound_ShouldReturn404() throws Exception {
        int nonExistentPilotId = 999;

        // Mock the service to throw an exception
        Mockito.doThrow(new EntityNotFoundException("Pilot not found"))
               .when(pilotService).deleteOne(nonExistentPilotId);

        mockMvc.perform(delete("/squadron/{pilotId}", nonExistentPilotId)
                        .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.message").value("Pilot not found")); // Optional, if you return error details

        Mockito.verify(pilotService, Mockito.times(1)).deleteOne(nonExistentPilotId);
    }

    @Test
    public void deleteOne_InvalidPilotId_ShouldReturn400() throws Exception {
        int invalidPilotId = -1;

        mockMvc.perform(delete("/squadron/{pilotId}", invalidPilotId)
                        .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest());

        Mockito.verify(pilotService, Mockito.never()).deleteOne(Mockito.anyInt());
    }

    @Test
    public void deleteOne_InternalServerError_ShouldReturn500() throws Exception {
        int pilotId = 1;

        Mockito.doThrow(new RuntimeException("Unexpected error"))
               .when(pilotService).deleteOne(pilotId);

        mockMvc.perform(delete("/squadron/{pilotId}", pilotId)
                        .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isInternalServerError())
               .andExpect(jsonPath("$.message").value("Unexpected error")); // Optional, if you return error details

        Mockito.verify(pilotService, Mockito.times(1)).deleteOne(pilotId);
    }

    
}
