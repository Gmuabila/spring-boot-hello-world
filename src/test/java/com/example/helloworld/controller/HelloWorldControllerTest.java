package com.example.helloworld.controller;

import com.example.helloworld.domaine.CaseTask;
import com.example.helloworld.dto.UpdateStatusDTO;
import com.example.helloworld.repositories.CaseTasksRepository;
import com.example.helloworld.services.CaseworkTaskServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerTest {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new JavaTimeModule());

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CaseworkTaskServices caseworkTaskServicesMock;

    @MockBean
    CaseTasksRepository caseTasksRepositoryMock;

    @Test
    public void shouldReturnExpectedMessage() throws Exception {

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    public void createTask_controller_happy_path_test() throws Exception {
        CaseTask caseTask = new CaseTask(1, "School", "Saint Mary", "started", null);

        when(caseworkTaskServicesMock.createTask(caseTask)).thenReturn(caseTask);
        MvcResult mvcResult = mockMvc.perform(post("/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody(caseTask))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        CaseTask caseTaskResponse = parseResponse(mvcResult, CaseTask.class);
        assertThat(caseTaskResponse.equals(caseTask));
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void retrieve_taskbyid_controller_happy_path_test() throws Exception {
        CaseTask caseTask = new CaseTask(3, "Library", "Reading all day", "Started", null);
        when(caseworkTaskServicesMock.getTaskById(3)).thenReturn(caseTask);
        MvcResult requestResult = mockMvc.perform(get("/tasksbyid/{idIn}", 3)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        CaseTask returnedCaseTask = parseResponse(requestResult, CaseTask.class);
        assertEquals(caseTask, returnedCaseTask);
    }

    @Test
    public void retrieve_allTasks_controller_happy_path_test() throws Exception {
        CaseTask caseTask = new CaseTask(3, "Library", "Reading all day", "Started", null);
        CaseTask caseTaskTwo = new CaseTask(2, "University", "Researching all day", "Started", null);
        List<CaseTask> caseTaskList = new ArrayList<>();
        caseTaskList.add(caseTask);
        caseTaskList.add(caseTaskTwo);
        when(caseworkTaskServicesMock.getAllTasks()).thenReturn(caseTaskList);
        MvcResult allTasksResult = mockMvc.perform(get("/allcasetasks")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = allTasksResult.getResponse().getContentAsString();
        TypeToken<List<CaseTask>> typeToken = new TypeToken<List<CaseTask>>() {
        };
        List<CaseTask> allTasksList = new Gson().fromJson(json, typeToken.getType());
        assertEquals(2, allTasksList.size());
    }

    public static String requestBody(Object request) {
        try {
            return MAPPER.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseResponse(MvcResult result, Class<T> responseClass) {
        try {
            String contentAsString = result.getResponse().getContentAsString();
            return MAPPER.readValue(contentAsString, responseClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void update_task_controller_happy_path_test() throws Exception {
        CaseTask caseTask = new CaseTask(3, "Library", "Reading all day", "Started", null);
        UpdateStatusDTO updateStatusDTO = new UpdateStatusDTO(3, "Completed");
        caseTask.setStatus(updateStatusDTO.getStatus());
        when(caseworkTaskServicesMock.updateTaskStatus(updateStatusDTO.getId(),
                updateStatusDTO.getStatus())).thenReturn(caseTask);
        MvcResult requestResult = mockMvc.perform(put("/updatestatus")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody(updateStatusDTO)))
                .andExpect(status().isOk())
                .andReturn();
        CaseTask updatedCaseTask = parseResponse(requestResult, CaseTask.class);
        assertEquals("Completed", updatedCaseTask.getStatus());
    }

    @Test
    public void delete_task_controller_happy_path_test() throws Exception {
        CaseTask caseTask = new CaseTask(3, "Library", "Reading all day", "Started", null);
        when(caseworkTaskServicesMock.deleteTask(3)).thenReturn(caseTask);
        MvcResult deletedTask = mockMvc.perform(delete("/deletetask/{idIn}", 3)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        CaseTask deletedCaseTask = parseResponse(deletedTask, CaseTask.class);
        assertEquals(caseTask, deletedCaseTask);
    }
}
