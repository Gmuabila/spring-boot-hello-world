package com.example.helloworld.services;


import com.example.helloworld.domaine.CaseTask;
import com.example.helloworld.exception.InvalidUserActionException;
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
//    private Map<Integer, CaseTask> caseTaskMap = new HashMap<>();

    public CaseTask createTask(CaseTask caseTask){
//        this.caseTaskMap.put(caseTask.getId(), caseTask);
//        Optional<CaseTask> foundBook = caseTasksRepository.findAll().stream().filter(x -> x..equalsIgnoreCase(bookIn.getTitle())
//                || x.getIsbn().equalsIgnoreCase(bookIn.getIsbn())).findFirst();
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
//        return caseTaskMap.get(idIn);
    }

    public List<CaseTask> getAllTasks() {
        Iterable<CaseTask> returnedTasks = caseTasksRepository.findAll();
        List<CaseTask> tasksList = (List<CaseTask>) returnedTasks;
        return tasksList;
//        return new ArrayList<>(caseTaskMap.values());
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
//        caseTaskMap.put(idIn, taskById);
//        return caseTaskMap.get(idIn);
    }

    public CaseTask deleteTask(Integer idIn){
        CaseTask returnedCaseTask = getTaskById(idIn);
        if(returnedCaseTask != null) {
            caseTasksRepository.deleteById(idIn);
            return returnedCaseTask;
        }else {
            return null;
        }
//            caseTaskMap.remove(idIn);
    }

}
