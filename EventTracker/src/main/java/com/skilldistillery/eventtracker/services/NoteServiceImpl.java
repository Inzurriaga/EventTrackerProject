package com.skilldistillery.eventtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.entities.Note;
import com.skilldistillery.eventtracker.repositories.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {
	@Autowired
	private NoteRepository repo;
	
	@Override
	public Note getNoteById(int id) {
		Optional<Note> noteOpt = repo.findById(id);
		if(noteOpt.isPresent()) {
			return noteOpt.get();
		}
		return null;
	}
	
	@Override
	public List<Note> getAllNotes() {
		return repo.findAll();
	}
	
	@Override
	public Note addNote(Note note) {
		repo.saveAndFlush(note);
		return note;
	}
	
	@Override
	public Note updateNote(Note note, int id) {
		System.out.println("im inide the service " + note.getTitle());
		Optional<Note> noteOpt = repo.findById(id);
		if(noteOpt.isPresent()) {
			note.setId(id);
			repo.saveAndFlush(note);
			return note;
		}
		return null;
	}
	
	@Override
	public boolean deleteNote(int id) {
		Optional<Note> noteOpt = repo.findById(id);
		if(noteOpt.isPresent()) {
			repo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
