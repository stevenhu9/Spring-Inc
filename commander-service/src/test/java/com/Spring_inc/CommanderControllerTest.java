package com.Spring_inc;

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
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.spring_inc.api.CommanderClient;
import com.spring_inc.controllers.SquadronController;
import com.spring_inc.dtos.ResponseDTO;
import com.spring_inc.dtos.SquadronDTO;
import com.spring_inc.models.Squadron;
import com.spring_inc.services.SquadronService;

@AutoConfigureMockMvc
@WebMvcTest(SquadronController.class)
public class SquadronControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @MockitoBean
    private SquadronService squadronService;
    
    @MockitoBean
    private CommanderClient commanderClient;

    @InjectMocks
    private SquadronController squadronController;

    Timestamp timestamp = Timestamp.from(Instant.now());

    Squadron SQUADRON_1 = new Squadron(1, "Fire Squad", "Austin, TX", timestamp, "Classified", 0, "Active", 1);
    Squadron SQUADRON_2 = new Squadron(2, "Sky Squad", "Dallas, TX", timestamp, "Secret", 0, "Active", 2);


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(squadronController).build();
    }

    @Test
    public void getAllSquadrons_success() throws Exception {
        List<Squadron> squadrons = Arrays.asList(SQUADRON_1, SQUADRON_2);

        when(squadronService.findAll()).thenReturn(ResponseEntity.ok(squadrons));

        mockMvc.perform(get("/squadron")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].squadronName").value("Fire Squad"))
                .andExpect(jsonPath("$[1].squadronName").value("Sky Squad"));

        verify(squadronService, times(1)).findAll();
    }

    @Test
    public void getSquadronById_success() throws Exception {
        when(squadronService.findById(1)).thenReturn(ResponseEntity.ok(SQUADRON_1));

        mockMvc.perform(get("/squadron/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.squadronName").value("Fire Squad"));

        verify(squadronService, times(1)).findById(1);
    }

    @Test
    public void addSquadron_success() throws Exception {
        SquadronDTO squadronDTO = new SquadronDTO("Fire Squad", "Austin, TX", timestamp, "Classified", 0, "Active", 1);
        when(squadronService.addOne(any(SquadronDTO.class))).thenReturn(ResponseEntity.ok(SQUADRON_1));

        String content = objectWriter.writeValueAsString(squadronDTO);

        mockMvc.perform(post("/squadron")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.squadronName").value("Fire Squad"));

        verify(squadronService, times(1)).addOne(any(SquadronDTO.class));
    }

    @Test
    public void updateSquadron_success() throws Exception {
        SquadronDTO squadronDTO = new SquadronDTO("Sky Squad Updated", "Dallas, TX", timestamp, "Secret", 0, "Active", 2);
        SQUADRON_2.setSquadronName("Sky Squad Updated");

        when(squadronService.updateOne(eq(2), any(SquadronDTO.class))).thenReturn(ResponseEntity.ok(SQUADRON_2));

        String content = objectWriter.writeValueAsString(squadronDTO);

        mockMvc.perform(put("/squadron/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.squadronName").value("Sky Squad Updated"));

        verify(squadronService, times(1)).updateOne(eq(2), any(SquadronDTO.class));
    }

    @Test
    public void deleteSquadronWithReplacement_success() throws Exception {
        ResponseDTO responseDTO = new ResponseDTO("Squadron deleted successfully", true);
        when(squadronService.deleteOne(1, 2)).thenReturn(ResponseEntity.ok(responseDTO));

        mockMvc.perform(delete("/squadron/1/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Squadron deleted successfully"));

        verify(squadronService, times(1)).deleteOne(1, 2);
    }

    @Test
    public void deleteSquadronWithoutReplacement_success() throws Exception {
        ResponseDTO responseDTO = new ResponseDTO("Squadron deleted successfully", true);
        when(squadronService.deleteOne(1)).thenReturn(ResponseEntity.ok(responseDTO));

        mockMvc.perform(delete("/squadron/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Squadron deleted successfully"));

        verify(squadronService, times(1)).deleteOne(1);
    }
    
    //fix: commander id not found
    @Test
    public void getCommanderOfSquadron_success() throws Exception {
    	when(squadronService.findById(1)).thenReturn(ResponseEntity.ok(SQUADRON_1));

        mockMvc.perform(get("/squadron/1/commander")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commanderId").value(1));

        verify(squadronService, times(1)).findById(1);
    }
    //fix: find access to pilot class
//    @Test
//    public void getPilotsInSquadron_success() throws Exception {
//    	 List<Pilot> pilots = Arrays.asList(SQUADRON_1, SQUADRON_2);
//
//         when(squadronService.findAll()).thenReturn(ResponseEntity.ok(squadrons));
//
//         mockMvc.perform(get("/squadron")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].squadronName").value("Fire Squad"))
//                 .andExpect(jsonPath("$[1].squadronName").value("Sky Squad"));
//
//         verify(squadronService, times(1)).findAll();
//    }
    
//    @Test
//    public void addPilotToSquadron_success() throws Exception {
//        SquadronDTO squadronDTO = new SquadronDTO("Fire Squad", "Austin, TX", timestamp, "Classified", 0, "Active", 1);
//        when(squadronService.addOne(any(SquadronDTO.class))).thenReturn(ResponseEntity.ok(SQUADRON_1));
//
//        String content = objectWriter.writeValueAsString(squadronDTO);
//
//        mockMvc.perform(post("/squadron")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.squadronName").value("Fire Squad"));
//
//        verify(squadronService, times(1)).addOne(any(SquadronDTO.class));
//    }
    
}
