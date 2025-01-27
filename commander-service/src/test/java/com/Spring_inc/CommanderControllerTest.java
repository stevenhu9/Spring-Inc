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

import com.Spring_inc.api.SquadronClient;
import com.Spring_inc.controllers.CommanderController;
import com.Spring_inc.dtos.CommanderDTO;
import com.Spring_inc.dtos.ResponseDTO;
import com.Spring_inc.models.Commander;
import com.Spring_inc.services.CommanderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@AutoConfigureMockMvc
@WebMvcTest(CommanderController.class)
public class CommanderControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @MockitoBean
    private CommanderService commanderService;
    
    @MockitoBean
    private SquadronClient squadronClient;

    @InjectMocks
    private CommanderController commanderController;

    Timestamp timestamp = Timestamp.from(Instant.now());

    Commander COMMANDER_1 = new Commander(1, "Jane Doe", "General", 10, "Infantry", "Active");
    Commander COMMANDER_2 = new Commander(2, "John Doe", "Low", 1, "Infantry", "Active");


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(commanderController).build();
    }

    @Test
    public void getAllCommanders_success() throws Exception {
        List<Commander> commanders = Arrays.asList(COMMANDER_1, COMMANDER_2);

        when(commanderService.findAll()).thenReturn(ResponseEntity.ok(commanders));

        mockMvc.perform(get("/commander")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].commanderName").value("Jane Doe"))
                .andExpect(jsonPath("$[1].commanderName").value("John Doe"));

        verify(commanderService, times(1)).findAll();
    }

    @Test
    public void getCommanderById_success() throws Exception {
        when(commanderService.findById(1)).thenReturn(ResponseEntity.ok(COMMANDER_1));

        mockMvc.perform(get("/commander/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commanderName").value("Jane Doe"));

        verify(commanderService, times(1)).findById(1);
    }

    @Test
    public void addCommander_success() throws Exception {
    	CommanderDTO commanderDTO = new CommanderDTO("Jane Doe", "General", "Infantry", 10, "Active");
        when(commanderService.addOne(any(CommanderDTO.class))).thenReturn(ResponseEntity.ok(COMMANDER_1));

        String content = objectWriter.writeValueAsString(commanderDTO);

        mockMvc.perform(post("/commander")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commanderName").value("Jane Doe"));

        verify(commanderService, times(1)).addOne(any(CommanderDTO.class));
    }

    @Test
    public void updateCommander_success() throws Exception {
    	CommanderDTO commanderDTO = new CommanderDTO("John Doe", "Low", "Infantry", 1, "Active");
        COMMANDER_2.setCommanderName("Jimmy Doe");

        when(commanderService.updateOne(eq(2), any(CommanderDTO.class))).thenReturn(ResponseEntity.ok(COMMANDER_2));

        String content = objectWriter.writeValueAsString(commanderDTO);

        mockMvc.perform(put("/commander/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commanderName").value("Jimmy Doe"));

        verify(commanderService, times(1)).updateOne(eq(2), any(CommanderDTO.class));
    }

    @Test
    public void deleteCommanderWithReplacement_success() throws Exception {
        ResponseDTO responseDTO = new ResponseDTO("Commander deleted successfully", true);
        when(commanderService.deleteOne(1, 2)).thenReturn(ResponseEntity.ok(responseDTO));

        mockMvc.perform(delete("/commander/1/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Commander deleted successfully"));

        verify(commanderService, times(1)).deleteOne(1, 2);
    }

    @Test
    public void deleteCommanderWithoutReplacement_success() throws Exception {
        ResponseDTO responseDTO = new ResponseDTO("Commander deleted successfully", true);
        when(commanderService.deleteOne(1)).thenReturn(ResponseEntity.ok(responseDTO));

        mockMvc.perform(delete("/commander/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Commander deleted successfully"));

        verify(commanderService, times(1)).deleteOne(1);
    }
    
}
