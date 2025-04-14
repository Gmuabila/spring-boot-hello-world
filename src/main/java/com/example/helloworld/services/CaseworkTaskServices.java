package com.example.helloworld.services;

import com.example.helloworld.domaine.CaseTask;
import com.example.helloworld.repositories.CaseTasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CaseworkTaskServices {

    @Autowired
    private final CaseTasksRepository caseTasksRepository;

    public CaseworkTaskServices(CaseTasksRepository caseTasksRepository) {
        this.caseTasksRepository = caseTasksRepository;
    }

    public CaseTask createTask(CaseTask caseTask){
        caseTasksRepository.save(caseTask);

        return caseTask;
    }
    public CaseTask getTaskById(Integer idIn) {
        Optional<CaseTask> taskFromDb = caseTasksRepository.findById(idIn);
        CaseTask returnedTask = new CaseTask();
        if(taskFromDb.isPresent()) {
            returnedTask = taskFromDb.get();
            return returnedTask;
        } else{
            return null;
        }
    }

    public List<CaseTask> getAllTasks() {
        Iterable<CaseTask> returnedTasks = caseTasksRepository.findAll();
        List<CaseTask> tasksList = (List<CaseTask>) returnedTasks;
        return tasksList;
    }

    public CaseTask updateTaskStatus(Integer idIn, String status){
        CaseTask taskById = getTaskById(idIn);
        if (taskById != null){
            taskById.setStatus(status);
            caseTasksRepository.save(taskById);
            return taskById;
        }else {
            return null;
        }
    }

    public CaseTask deleteTask(Integer idIn){
        CaseTask returnedCaseTask = getTaskById(idIn);
        if(returnedCaseTask != null) {
            caseTasksRepository.deleteById(idIn);
            return returnedCaseTask;
        }else {
            return null;
        }
    }

}
