package ua.pp.jjd.lmsnew.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.pp.jjd.lmsnew.dto.TrainerDTO;
import ua.pp.jjd.lmsnew.service.TrainerService;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrainerController.class)
@ComponentScan
public class TrainerControllerTest {

    private final String URL = "/trainers";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TrainerService trainerService;

    private TrainerDTO trainer1 = TrainerDTO.builder()
            .trainerId(1L)
            .name("Брюс Эккель")
            .build();
    private TrainerDTO trainer2 = TrainerDTO.builder()
            .trainerId(2L)
            .name("Яков Файн")
            .build();
    private TrainerDTO trainer3 = TrainerDTO.builder()
            .trainerId(3L)
            .name("Джошуа Блох")
            .build();

    @Before
    public void setUp() {
        when(trainerService.getAll())
                .thenReturn(Arrays.asList(trainer1, trainer2, trainer3));
        when(trainerService.getById(trainer1.getTrainerId())).thenReturn(trainer1);
        when(trainerService.add(trainer1)).thenReturn(trainer1);
        when(trainerService.update(trainer1)).thenReturn(trainer1);
    }

    @Test
    public void getAll() throws Exception {
        String result = objectMapper.
                writeValueAsString(Arrays.asList(trainer1, trainer2, trainer3));
        mvc.perform(get(URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }

    @Test
    public void getById() throws Exception {
        mvc.perform(get(URL + "/" + trainer1.getTrainerId()))
                .andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception {
        String requestBody = objectMapper.writeValueAsString(trainer1);
        mvc.perform(post(URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string(requestBody));
    }

    @Test
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(URL + "/" + trainer1.getTrainerId()))
                .andExpect(status().isOk());

    }

    @Test
    public void update() throws Exception {
        String requestBody = objectMapper.writeValueAsString(trainer1);
        mvc.perform(put(URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string(requestBody));
    }

}