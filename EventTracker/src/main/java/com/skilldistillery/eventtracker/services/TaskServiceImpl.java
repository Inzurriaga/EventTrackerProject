package com.skilldistillery.eventtracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository repo;

}
