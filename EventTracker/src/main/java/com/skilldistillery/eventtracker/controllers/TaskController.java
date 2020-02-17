package com.skilldistillery.eventtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.eventtracker.entities.Note;
import com.skilldistillery.eventtracker.entities.Task;
import com.skilldistillery.eventtracker.services.TaskService;

@RequestMapping("api")
@RestController
public class TaskController {
	@Autowired
	private TaskService srv;
	
//	@GetMapping("notes/{noteId}/tasks")
//	public List<Task> getAllTaskbyNoteId() {
//		
//		return null;
//	}
//	
//	@GetMapping("notes/{noteId}/tasks/{taskId}")
//	public Task getTaskById(@PathVariable Integer noteId, @PathVariable Integer taskId) {
//		return srv.getTaskById(noteId, taskId);
//	}
//	
//	@PostMapping("notes/{noteId}/tasks")
//	public Task createTask(@RequestBody Task task, @PathVariable Integer noteId) {
//		return null;
//	}
//	
//	@PutMapping("notes/{noteId}/tasks/{taskId}")
//	public void updateTask(@RequestBody Task task, @PathVariable Integer noteId, @PathVariable Integer taskId) {
//		
//	}

}
