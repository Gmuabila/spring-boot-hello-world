package com.example.helloworld.services;

import com.example.helloworld.repositories.CaseTasksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
//        CaseTask caseTask = new CaseTask(5, "Library", "description", "Reading all day", null);
//        when(caseTasksRepository.save(caseTask)).thenReturn(caseTask);

//        assertEquals(caseworkTaskServices.createTask(caseTask), caseTask);


    }


}
