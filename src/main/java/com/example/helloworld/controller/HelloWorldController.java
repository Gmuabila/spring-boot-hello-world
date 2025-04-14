package com.example.helloworld.controller;

import com.example.helloworld.domaine.CaseTask;
import com.example.helloworld.dto.UpdateStatusDTO;
import com.example.helloworld.services.CaseworkTaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloWorldController {

    @Autowired
    CaseworkTaskServices caseworkTaskServices;

    @GetMapping("/hello")
    public String sendGreetings() {
        return "Hello, World!";
    }


    @PostMapping("/createTask")
    public ResponseEntity<CaseTask> createTask(@RequestBody @Validated CaseTask newTask){
        CaseTask task = caseworkTaskServices.createTask(newTask);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/tasksbyid/{idIn}")
    public ResponseEntity<CaseTask> getTaskbyId(@PathVariable("idIn") Integer idIn){
        CaseTask returnedTask = caseworkTaskServices.getTaskById(idIn);

        return ResponseEntity.ok(returnedTask);
    }

    @GetMapping("/allcasetasks")
    public ResponseEntity<List<CaseTask>> retrieveAllTasks() {
        List<CaseTask> caseTaskList = caseworkTaskServices.getAllTasks();
        return ResponseEntity.ok(caseTaskList);
    }

    @PutMapping("/updatestatus")
    public CaseTask updateTaskStatus(@RequestBody UpdateStatusDTO updateStatusDTO){
        return caseworkTaskServices.updateTaskStatus(updateStatusDTO.getId(), updateStatusDTO.getStatus());
    }

    @DeleteMapping("/deletetask/{idIn}")
    public CaseTask deleteTask(@PathVariable("idIn") Integer idIn){
        return caseworkTaskServices.deleteTask(idIn);
    }

}
