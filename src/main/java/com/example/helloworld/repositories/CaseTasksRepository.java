package com.example.helloworld.repositories;

import com.example.helloworld.domaine.CaseTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseTasksRepository extends JpaRepository<CaseTask, Integer> {
}
