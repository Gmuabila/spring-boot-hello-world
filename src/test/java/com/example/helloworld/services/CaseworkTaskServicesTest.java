package com.example.helloworld.services;

import com.example.helloworld.domaine.CaseTask;
import com.example.helloworld.repositories.CaseTasksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class CaseworkTaskServicesTest {
    private CaseworkTaskServices caseworkTaskServices;

    @Mock
    private CaseTasksRepository caseTasksRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        caseworkTaskServices = new CaseworkTaskServices(caseTasksRepository);
    }

    @Test
    public void second_test(){

    }

    @Test
    public void createTask_happy_path_test() {
        CaseTask caseTask = new CaseTask(5, "Library", "Reading all day", "Started", null);
        when(caseTasksRepository.save(caseTask)).thenReturn(caseTask);

        assertEquals(caseworkTaskServices.createTask(caseTask), caseTask);
    }

//    @Test
//    public void createTask_Negative_path_test() {
//        CaseTask caseTask = null;
//        when(caseTasksRepository.save(caseTask)).thenReturn(caseTask);
//
//        assertEquals(caseworkTaskServices.createTask(caseTask), caseTask);
//    }

    @Test
    public void retrieveTaskbyId_happy_path_test() {
        CaseTask caseTask = new CaseTask(1, "Library", "Reading all day", "Started", null);
        when(caseTasksRepository.findById(1)).thenReturn(Optional.of(caseTask));

        assertEquals(caseworkTaskServices.getTaskById(1), caseTask);
    }

    @Test
    public void updateStatus_happy_path_test() {
        CaseTask caseTask = new CaseTask(1, "Library", "Reading all day", "Started", null);
        CaseTask caseTaskTwo = new CaseTask(1, "Library", "Reading all day", "Completed", null);
        when(caseTasksRepository.findById(1)).thenReturn(Optional.of(caseTask));
        when(caseTasksRepository.save(caseTask)).thenReturn(caseTask);
        assertEquals(caseworkTaskServices.updateTaskStatus(1, "Completed"), caseTaskTwo);
    }

    @Test
    public void deleteTask_happy_path_test() {
        CaseTask caseTask = new CaseTask(1, "Library", "Reading all day", "Started", null);
        when(caseTasksRepository.findById(1)).thenReturn(Optional.of(caseTask));
        doNothing().when(caseTasksRepository).deleteById(1);
        assertEquals(caseworkTaskServices.deleteTask(1), caseTask);  //Is it ok for the delete task to return the deleted object???
    }

}
