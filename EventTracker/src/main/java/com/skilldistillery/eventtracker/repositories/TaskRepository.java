package com.skilldistillery.eventtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.eventtracker.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
