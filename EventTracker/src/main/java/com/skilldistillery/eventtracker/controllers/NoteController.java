package com.skilldistillery.eventtracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.eventtracker.entities.Note;
import com.skilldistillery.eventtracker.services.NoteService;

@RequestMapping("api")
@RestController
public class NoteController {
	@Autowired
	private NoteService srv;
	@GetMapping("notes/{id}")
	public Note getNoteById(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse res) {
		Note note = srv.getNoteById(id);
		if(note != null) {
			StringBuffer reqLocation = req.getRequestURL();
			String location = reqLocation.append("/notes/").append(id).toString();
			res.setStatus(202);
			res.setHeader("location", location);
			return note;
		}
		res.setStatus(404);
		return null;
	}
	
	@GetMapping("notes")
	public List<Note> getAllNotes(HttpServletRequest req, HttpServletResponse res) {
		StringBuffer reqLocation = req.getRequestURL();
		String location = reqLocation.append("/notes").toString();
		res.setStatus(202);
		res.setHeader("location", location);
		return srv.getAllNotes();
	}
	
	@PostMapping("notes")
	public Note addNote(@RequestBody Note note, HttpServletRequest req, HttpServletResponse res) {
		try {
			Note addedNote = srv.addNote(note);
			StringBuffer reqLocation = req.getRequestURL();
			String location = reqLocation.append("/notes/").append(addedNote.getId()).toString();
			res.setStatus(201);
			res.setHeader("location", location);
			return addedNote;
		} catch(Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("notes/{id}")
	public Note updateNote(@RequestBody Note note, @PathVariable Integer id, HttpServletRequest req, HttpServletResponse res) {
		try {
			Note updatedNote = srv.updateNote(note, id);
			StringBuffer reqLocation = req.getRequestURL();
			String location = reqLocation.append("/notes/").append(updatedNote.getId()).toString();
			res.setStatus(201);
			res.setHeader("location", location);
			return updatedNote;
		} catch(Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("notes/{id}")
	public void deleteNote(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse res) {
		boolean deleted = srv.deleteNote(id);
		if(deleted) {
			StringBuffer reqLocation = req.getRequestURL();
			String location = reqLocation.append("/notes/").append(id).toString();
			res.setStatus(200);
			res.setHeader("location", location);
		} else {
			res.setStatus(404);
		}
	}
}
