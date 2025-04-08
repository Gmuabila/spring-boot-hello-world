package com.example.helloworld.domaine;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class CaseTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NonNull
    private String title;
    private String description;
    @NonNull
    private String status;
    @NonNull
    private Date due;

    public CaseTask(Integer id, @NonNull String title, String description, String status, @NonNull Date due) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.due = due;
    }

    public CaseTask() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NonNull
    public Date getDue() {
        return due;
    }

    public void setDue(@NonNull Date due) {
        this.due = due;
    }
}
